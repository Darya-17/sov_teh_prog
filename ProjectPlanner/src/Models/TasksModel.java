package Models;

import Utils.DateUtils;
import Utils.JsonUtils;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TasksModel {
    // Модель для управления заданиями
    private final String tasksFileName = "tasks.json";
    private final String idCounterFileName = "idCounterFileName.json";


    public List<Task> getAllTasks() {
        var tasks = getTasksFromFile();
        if (tasks == null)
            return new ArrayList<>();
        else {
            List<Task> outTasks = new ArrayList<>();
            for (Object taskJson : tasks) {
                outTasks.add(getTaskFromJson((JsonObject) taskJson));
            }
            return outTasks;
        }
    }

    public List<Task> getCompletedTask() {
        var tasks = getAllTasks();
        var outTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.isCompleted())
                outTasks.add(task);
        }
        return outTasks;
    }

    public List<Task> getUncompletedTask() {
        var tasks = getAllTasks();
        var outTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (!task.isCompleted())
                outTasks.add(task);
        }
        return outTasks;
    }

    private Task getTaskFromJson(JsonObject jObj) {
        // преобразует объект JsonObject в класс modes.Task
        var task = new Task();
        task.setId(Integer.parseInt(jObj.get("id").toString()));
        task.setName((String) jObj.get("name"));
        task.setCreatedDate(DateUtils.parseDate((String) jObj.get("creation_date"), true));
        task.setCompletedDate(DateUtils.parseDate((String) jObj.get("completed_date"), true));
        task.setRealCompletedDate(DateUtils.parseDate((String) jObj.get("realcompleted_date"), true));
        task.setDescription((String) jObj.get("description"));
        task.setCompleted((Boolean) jObj.get("is_completed"));
        return task;
    }

    private JsonArray getTasksFromFile() {
        // получает задания из json

        return (JsonArray) JsonUtils.getJsonFromFile(tasksFileName);
    }

    private void saveTask(Task task) {
        var tasksJson = getTasksFromFile();
        if (tasksJson == null)
            tasksJson = new JsonArray();
        JsonObject jObj = null;
        for (Object o : tasksJson) {
            var taskJson = (JsonObject) o;
            var id = taskJson.get("id");
            if (Utils.BaseUtils.parseIntOrNull(id.toString()) == task.getId()){
                jObj = (JsonObject) o;
            }
        }
        if (jObj == null){
            jObj = new JsonObject();
            tasksJson.add(jObj);
        }
        jObj.put("id", task.getId());
        jObj.put("name", task.getName());
        jObj.put("creation_date", DateUtils.dateToString(task.getCreationDate(), true));
        jObj.put("realcompleted_date", DateUtils.dateToString(task.getRealCompletedDate(), true));
        jObj.put("completed_date", DateUtils.dateToString(task.getCompletedDate(), true));
        jObj.put("description", task.getDescription());
        jObj.put("is_completed", task.isCompleted());
        JsonUtils.saveJsonToFile(tasksFileName, tasksJson.toJson());
    }

    private void UpdateIdCounter(int i) {
        // обновляет id в файле
        var jObj = new JsonObject();
        jObj.put("currentId", i);
        JsonUtils.saveJsonToFile(idCounterFileName, jObj.toJson());
    }

    private int GetIdCounter() {
        // получает id из файла
        var result = (JsonObject)JsonUtils.getJsonFromFile(idCounterFileName);
        if (result == null) {
            var id = 0;
            UpdateIdCounter(id);
            return id;
        }
        return Integer.parseInt(result.get("currentId").toString());
    }

    public void createTask(Task task) {
        // задает дату создания и id заданию и сохраняет его
        task.setCreatedDate(new Date());
        var id = GetIdCounter();
        task.setId(id);
        UpdateIdCounter(id + 1);
        saveTask(task);
    }

    public void editTask(Task task) {
        // сохраняет отредактированное задание
        saveTask(task);
    }

    public void setTaskCompleted(Task task, boolean completed) {
        // отмечает задание правильным

        task.setCompleted(completed);
        saveTask(task);
    }

    public void deleteTask(Task task) {
        // удаляет задание
        var tasksJson = getTasksFromFile();
        JsonObject jObj = null;
        for (Object o : tasksJson) {
            var taskJson = (JsonObject) o;
            var id = taskJson.get("id");
            if (Utils.BaseUtils.parseIntOrNull(id.toString()) == task.getId()){
                jObj = (JsonObject) o;
            }
        }
        if (jObj != null){
            tasksJson.remove(jObj);
        }
        tasksJson.removeIf(i -> Objects.equals(((JsonObject) i).get("id").toString(), Integer.toString(task.getId())));
        JsonUtils.saveJsonToFile(tasksFileName, tasksJson.toJson());
    }

}

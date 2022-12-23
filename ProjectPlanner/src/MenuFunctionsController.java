import Models.Task;
import Models.TasksModel;
import Utils.ConsoleUtils;
import Utils.DateUtils;
import Utils.TaskUtils;

import java.text.MessageFormat;
import java.util.*;
public class MenuFunctionsController {
    // Все функции планировщика заданий
    private final TasksModel tasksModel;
    private static String prompt = "\nВведите слово отменить, если хотите отменить текущую операцию";

    public MenuFunctionsController(TasksModel taskModel) {
        this.tasksModel = taskModel;
    }

    private void displayTasks(List<Task> tasks) {
        // Вывод полученных заданий в консоль
        var output = System.out;
        if (tasks.size() == 0) {
            System.out.println("Задания не найдены");
            return;
        }
        var format = "%-10s%-50s%-20s%-20s%-20s%n";
        output.printf(format, "id", "Название", "Дата создания", "Дата выполнения", "Выполнена");
        output.println();
        for (Task task : tasks) {
            String completed;
            if (task.isCompleted())
                completed = Optional.ofNullable(DateUtils.dateToString(task.getRealCompletedDate(), true)).orElse("-");
            else
                completed = "Нет";
            output.printf(format, Optional.of(Integer.toString(task.getId())).orElse("-"),
                    Optional.ofNullable(task.getName()).orElse("-"),
                    Optional.ofNullable(DateUtils.dateToString(task.getCreationDate(), true)).orElse("-"),
                    Optional.ofNullable(DateUtils.dateToString(task.getCompletedDate(), true)).orElse("-"),
                    completed);
            output.println();
        }

    }

    private void displayTasksCompletionDate(List<Task> tasks) {
        // Вывод заданий по дате выполнения
        System.out.println("Введите дату выполнения задачи\nНапример: 01.01.2022" + prompt);
        Date dateOutput;
        List<Task> outputTasks = new ArrayList<>();
        while (true) {
            var dateString = ConsoleUtils.getNotEmptyString();
            if (dateString == null  || dateString.isEmpty() || dateString.isBlank())
                return;
            var date = DateUtils.parseDate(dateString, false);
            if (date != null) {
                dateOutput = date;
                break;
            }
            System.out.println("Введен неверный формат даты!\n Введите еще раз");
        }
        var dateString = DateUtils.dateToString(dateOutput, false);
        for (Task task : tasks) {
            if (Objects.equals(DateUtils.dateToString(task.getCompletedDate(), false), dateString)){
                outputTasks.add(task);
            }
        }
        displayTasks(outputTasks);
    }

    private void displayTasksCreationDate(List<Task> tasks) {
        // Вывод заданий по дате создания
        System.out.println("Введите дату выполнения задачи\nНапример: 01.01.2022" + prompt);
        Date dateOutput;
        List<Task> outputTasks = new ArrayList<>();
        while (true) {
            var dateString = ConsoleUtils.getNotEmptyString();
            if (dateString == null  || dateString.isEmpty() || dateString.isBlank())
                return;
            var date = DateUtils.parseDate(dateString, false);
            if (date != null) {
                dateOutput = date;
                break;
            }
            System.out.println("Введен неверный формат даты!\n Введите еще раз");
        }
        var dateString = DateUtils.dateToString(dateOutput, false);

        for (Task task : tasks) {
            if (Objects.equals(DateUtils.dateToString(task.getCreationDate(), false), dateString)){
                outputTasks.add(task);
            }
        }
        displayTasks(outputTasks);
    }

    // функции, вызываемые из главного меню
    public void createTask() {
        var task = new Task();
        var res = TaskUtils.FillTask(task, false);
        if (!res)
            return;
        this.tasksModel.createTask(task);
        System.out.println(MessageFormat.format("Задача \"{0}\" успешно создана!", Optional.ofNullable(task.getName()).orElse("---")));
    }

    public void deleteTask() {
        System.out.println("Введите id задания для удаления" + prompt);
        var tasks = tasksModel.getAllTasks();
        displayTasks(tasks);
        var task = TaskUtils.getTaskById(tasks);
        if (task == null)
            return;
        this.tasksModel.deleteTask(task);
        System.out.println(MessageFormat.format("Задача \"{0}\" по id {1} успешно удалена!",
                Optional.ofNullable(task.getName()).orElse("-"), Optional.of(Integer.toString(task.getId())).orElse("-")));
    }

    public void editTask() {
        System.out.println("Введите id задания для редактирования" + prompt);
        var tasks = tasksModel.getAllTasks();
        displayTasks(tasks);
        var task = TaskUtils.getTaskById(tasks);
        if (task == null)
            return;
        var res = TaskUtils.FillTask(task, true);
        if (!res)
            return;
        this.tasksModel.editTask(task);
        System.out.println(MessageFormat.format("Задача \"{0}\" по id {1} успешно сохранена!", Optional.ofNullable(task.getName()).orElse("-"),
                Optional.of(Integer.toString(task.getId())).orElse("-")));
    }

    public void displayAllTasks() {
        displayTasks(tasksModel.getAllTasks());
    }

    public void displayCompletedTasks() {
        displayTasks(tasksModel.getCompletedTask());
    }

    public void displayUncompletedTasks() {
        displayTasks(tasksModel.getUncompletedTask());
    }

    public void displayAllTasksByDateOfCompleted() {
        displayTasksCompletionDate(tasksModel.getAllTasks());
    }

    public void displayCompletedTasksByDateOfCompleted() {
        displayTasksCompletionDate(tasksModel.getCompletedTask());
    }

    public void displayUncompletedTasksByDateOfCompleted() {
        displayTasksCompletionDate(tasksModel.getUncompletedTask());
    }

    public void displayAllTasksByDateOfCreation() {
        displayTasksCreationDate(tasksModel.getAllTasks());
    }

    public void displayCompletedTasksByDateOfCreation() {
        displayTasksCreationDate(tasksModel.getCompletedTask());
    }

    public void displayUncompletedTasksByDateOfCreation() {
        displayTasksCreationDate(tasksModel.getUncompletedTask());
    }

    public void displayFullInformation() {
        var tasks = tasksModel.getAllTasks();
        displayTasks(tasks);
        var task = TaskUtils.getTaskById(tasks);
        if (task == null)
            return;
        System.out.println("Введите id задания для вывода полной информации");
        System.out.println(MessageFormat.format("id - {0}", Optional.of(Integer.toString(task.getId())).orElse("-")));
        System.out.println(MessageFormat.format("Название - {0}", Optional.ofNullable(task.getName()).orElse("-")));
        System.out.println(MessageFormat.format("Дата создания - {0}",
                Optional.ofNullable(DateUtils.dateToString(task.getCreationDate(), true)).orElse("-")));
        System.out.println(MessageFormat.format("Дата выполнения - {0}",
                Optional.ofNullable(DateUtils.dateToString(task.getCompletedDate(), true)).orElse("-")));
        String completed;
        if (task.isCompleted())
            completed = DateUtils.dateToString(task.getRealCompletedDate(), true);
        else
            completed = "Нет";
        System.out.println(MessageFormat.format("Выполнена - {0}",
                completed));
        System.out.println(MessageFormat.format("Описание - {0}", Optional.ofNullable(task.getDescription()).orElse("-")));
    }

    public void markTaskCompleted() {
        System.out.println("Введите id задания для выполнения" + prompt);
        var tasks = tasksModel.getUncompletedTask();
        displayTasks(tasks);
        var task = TaskUtils.getTaskById(tasks);
        if (task == null)
            return;
        this.tasksModel.setTaskCompleted(task, true);
        System.out.println(MessageFormat.format("Задача \"{0}\" по id {1} успешно отмечена выполненной!",
                Optional.ofNullable(task.getName()).orElse("-"), Optional.of(Integer.toString(task.getId())).orElse("-")));
    }
}

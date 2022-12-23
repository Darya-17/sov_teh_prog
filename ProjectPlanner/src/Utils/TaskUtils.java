package Utils;

import Models.Task;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

public class TaskUtils {
    // Вспомогательные функции для работы с заданиями
    private static final PrintStream out = System.out;

    private static boolean setCompletedDateForTask(Task task, boolean is_edit) {
        // Заполнение даты в задании при редактировании или создании
        while (true) {
            var dateString = is_edit ? ConsoleUtils.getString() : ConsoleUtils.getNotEmptyString();
            if (dateString == null)
                return false;
            if (is_edit)
                if (dateString.isEmpty() || dateString.isBlank())
                    return true;
            var date = DateUtils.parseDate(dateString, true);
            if (date != null) {
                task.setCompletedDate(date);
                return true;
            }
            System.out.println("Введен неверный формат даты!\n Введите еще раз");
        }
    }


    public static boolean FillTask(Task task, boolean is_edit) {
        // Заполнение задания при редактировании или создании
        var prompt = "\nВведите слово отменить, если хотите отменить текущую операцию";
        if (is_edit)
            prompt += "\nНажмите enter, если нужно пропустить значение";
        out.println("Введите название" + prompt);
        var name = is_edit ? ConsoleUtils.getString() : ConsoleUtils.getNotEmptyString();
        if (name == null)
            return false;
        if (is_edit) {
            if (!name.isBlank() && !name.isEmpty())
                task.setName(name);
        } else
            task.setName(name);
        out.println("Введите дату выполнения задачи\nНапример: 01.01.2022 00:00" + prompt);
        var res = setCompletedDateForTask(task, is_edit);
        if (!res)
            return false;
        out.println("Введите описание" + prompt);
        var description = is_edit ? ConsoleUtils.getString() : ConsoleUtils.getNotEmptyString();
        if (description == null)
            return false;
        if (is_edit) {
            if (!description.isBlank() && !description.isEmpty())
                task.setDescription(description);
        } else
            task.setDescription(description);
        if (is_edit) {
            out.println("Отменить выполнение задания?\nВведите \"да\" если отменить" + prompt);
            while (true) {
                var answer = ConsoleUtils.getString();
                if (answer == null)
                    return false;
                if (answer.toLowerCase(Locale.ROOT).equals("да")) {
                    task.setCompleted(false);
                    break;
                } else if (answer.isEmpty() || !answer.isBlank())
                    break;
                System.out.println("Неверное значение, введите enter,\n если хотите пропустить этот пункт");
            }
        }
        return true;
    }

    public static Task getTaskById(List<Task> tasks) {
        // Получение задания по id из консоли
        Integer id;
        while (true) {
            var idString = ConsoleUtils.getString();
            if (idString == null)
                return null;
            id = BaseUtils.parseIntOrNull(idString);
            if (id != null)
                for (Task task : tasks)
                    if (task.getId() == id) {
                        return task;
                    }
            System.out.println(MessageFormat.format("Неверный индекс задания {0}!\nВведите еще раз", idString));
        }
    }

}

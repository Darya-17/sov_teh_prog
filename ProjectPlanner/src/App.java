import Models.TasksModel;
import Utils.BaseUtils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private TasksModel tasksModel;
    private static String[] MenuItems; // Все пункты меню планировщика
    private MenuFunctionsController menuActionsController;

    public App() {
        tasksModel = new TasksModel();
        menuActionsController = new MenuFunctionsController(tasksModel);
        MenuItems = new String[]{
                "Создать задание",
                "Удалить задание",
                "Редактировать задание",
                "Вывести все задания",
                "Вывести выполненные задания",
                "Вывести невыполненные задания",
                "Вывести все задания за определенную дату выполнения",
                "Вывести выполненные задания за определенную дату выполнения",
                "Вывести невыполненные задания за определенную дату выполнения",
                "Вывести все задания за определенную дату создания",
                "Вывести выполненные задания за определенную дату создания",
                "Вывести невыполненные задания за определенную дату создания",
                "Вывести детальную информацию о задании",
                "Выполнить задание",
        };
    }

    private void printMenu() {
        // Выводит все пункты меню
        for (int i = 0; i < MenuItems.length; i++) {
            System.out.println(MessageFormat.format("{0} {1}", i + 1, MenuItems[i]));
        }
    }
    private void runFunctionById(int num){
        // Запуск функции по выбору из меню
        switch (num) {
            case 1 -> menuActionsController.createTask();
            case 2 -> menuActionsController.deleteTask();
            case 3 -> menuActionsController.editTask();
            case 4 -> menuActionsController.displayAllTasks();
            case 5 -> menuActionsController.displayCompletedTasks();
            case 6 -> menuActionsController.displayUncompletedTasks();
            case 7 -> menuActionsController.displayAllTasksByDateOfCompleted();
            case 8 -> menuActionsController.displayCompletedTasksByDateOfCompleted();
            case 9 -> menuActionsController.displayUncompletedTasksByDateOfCompleted();
            case 10 -> menuActionsController.displayAllTasksByDateOfCreation();
            case 11 -> menuActionsController.displayCompletedTasksByDateOfCreation();
            case 12 -> menuActionsController.displayUncompletedTasksByDateOfCreation();
            case 13 -> menuActionsController.displayFullInformation();
            case 14 -> menuActionsController.markTaskCompleted();
        }
    }
    public void Start() {
        // Запуск программы
        var input = new Scanner(System.in);
        var exit = false;
        System.out.println("Планировщик заданий - версия 1.0.0");
        while (true) {
            System.out.println("Введите номер пункта меню, чтобы выполнить его");
            printMenu();
            System.out.println("Для выхода из программы введите \"выйти\" и нажмите enter");
            Integer num = null;
            while (true) {
                var result = input.nextLine();
                if (result.toLowerCase(Locale.ROOT).equals("выйти")){
                    exit = true;
                    break;
                }
                num = BaseUtils.parseIntOrNull(result);
                if (num != null)
                    if (num > 0 && num <= MenuItems.length)
                        break;
                System.out.println(MessageFormat.format("Неверный индекс задания {0}!\nВведите еще раз", result));
            }
            if (exit){
                System.out.println("До свидания!");
                return;
            }
            runFunctionById(num);
            System.out.println("Для выхода в главное меню нажмите enter");
            input.nextLine();
        }
    }
}

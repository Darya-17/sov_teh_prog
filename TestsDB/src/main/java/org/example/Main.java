package org.example;

import org.postgresql.jdbc.PgResultSet;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {


    private static void InsertQuestions(DbManager dbManager) throws SQLException {
        dbManager.InsertQuestion("Сколько будет 2+2*2", 2, true);
        dbManager.InsertQuestion("Зачем мы существуем?", 2, true);
        dbManager.InsertQuestion("Сколько часов в сутках?", 2, true);
    }

    private static void InsertSubjects(DbManager dbManager) throws SQLException {
        dbManager.InsertSubject("Математика");
        dbManager.InsertSubject("Обществознание");
        dbManager.InsertSubject("Русский язык");
    }

    private static void InsertSchedules(DbManager dbManager) throws SQLException {
        dbManager.InsertSchedule(2, 3, 10, LocalDate.parse("2023-04-01"), LocalTime.parse("08:00:00"),
                LocalDate.parse("2023-04-01"), LocalTime.parse("08:10:00"), true);
        dbManager.InsertSchedule(3, 2, 10, LocalDate.parse("2023-04-01"), LocalTime.parse("08:00:00"),
                LocalDate.parse("2023-04-01"), LocalTime.parse("08:10:00"), true);
        dbManager.InsertSchedule(4, 2, 10, LocalDate.parse("2023-04-01"), LocalTime.parse("08:00:00"),
                LocalDate.parse("2023-04-01"), LocalTime.parse("08:10:00"), true);
    }

    private static void InsertTests(DbManager dbManager) throws SQLException {
        dbManager.InsertTestList("Промежуточная аттестация", 1, 6);
        dbManager.InsertTestList("Итоговый экзамен", 2, 6);
    }

    private static void InsertTestQuestionRelations(DbManager dbManager) throws SQLException {
        dbManager.InsertTestQuestionRelation(2, 31);
        dbManager.InsertTestQuestionRelation(2, 32);
        dbManager.InsertTestQuestionRelation(3, 33);
    }

    private static void InsertStudentAnswers(DbManager dbManager) throws SQLException {
        dbManager.InsertStudentAnswer(2, 3, 181);
        dbManager.InsertStudentAnswer(2, 3, 184);
        dbManager.InsertStudentAnswer(2, 4, 181);
        dbManager.InsertStudentAnswer(2, 4, 186);

        dbManager.InsertStudentAnswer(3, 4, 181);
        dbManager.InsertStudentAnswer(3, 4, 186);
        dbManager.InsertStudentAnswer(3, 4, 181);
        dbManager.InsertStudentAnswer(3, 4, 186);
    }

    private static void InsertAnswers(DbManager dbManager) throws SQLException {
        dbManager.InsertAnswer(31, "4", true);
        dbManager.InsertAnswer(31, "3", false);
        dbManager.InsertAnswer(31, "10", false);
        dbManager.InsertAnswer(32, "Просто так", false);
        dbManager.InsertAnswer(32, "Потому что так надо", false);
        dbManager.InsertAnswer(32, "На этот вопрос нет четкого ответа", true);
        dbManager.InsertAnswer(33, "24", true);
        dbManager.InsertAnswer(33, "25", false);
        dbManager.InsertAnswer(33, "12", false);
    }

    private static void InsertUsers(DbManager dbManager) throws SQLException {
        dbManager.InsertUser("Николай", "Федоров", "Васильевич", "nikola", "123",
                4, 1);
        dbManager.InsertUser("Василий", "Иванов", "Сергеевич", "vas", "1234",
                3, 1);
        dbManager.InsertUser("Алексей", "Соколов", "Алексеевич", "alex", "qwerty",
                2, 1);
        dbManager.InsertUser("Геннадий", "Петров", "Петрович", "gen", "4321",
                1, 2);
        dbManager.InsertUser("Андрей", "Зайцев", "Александрович", "andr", "1234",
                1, 2);
        dbManager.InsertUser("Светлана", "Петрова", "Петровна", "sveta", "4321",
                2, 2);
        dbManager.InsertUser("Людмила", "Иванова", "Васильевна", "luda", "123",
                3, 3);
        dbManager.InsertUser("Анна", "Гагарина", "Николаевна", "anna", "4321",
                4, 3);
        dbManager.InsertUser("Григорий", "Григорьев", "Григорьевич", "gosha", "1234",
                1, 3);
    }

    private static String formStringFromResultQuery(PgResultSet set) throws SQLException {
        var metaData = set.getMetaData();
        var columnCount = metaData.getColumnCount();
        var headerString = "";
        String resultString = "";
        for (int i = 1; i <= columnCount; i++) {
            headerString += String.format("%1$-" + metaData.getColumnDisplaySize(i) + "s", metaData.getColumnLabel(i));
        }
        resultString += headerString;
        while (set.next()) {
            var rowString = "";
            for (int i = 1; i <= columnCount; i++) {
                rowString += String.format("%1$-" + metaData.getColumnDisplaySize(i) + "s", set.getString(i));
            }
            resultString += rowString + "\n";
        }
        return resultString;
    }


    private static void ShowTeachers(Statement stmt) throws SQLException {
        PgResultSet teachers = (PgResultSet) stmt.executeQuery(
                "SELECT * " +
                        "FROM public.user " +
                        "WHERE role_id = 2");
        System.out.println(formStringFromResultQuery(teachers));
    }

    private static void ShowStudents(Statement stmt) throws SQLException {
        PgResultSet teachers = (PgResultSet) stmt.executeQuery(
                "SELECT * " +
                        "FROM public.user " +
                        "WHERE role_id = 1");
        System.out.println(formStringFromResultQuery(teachers));
    }

    private static void ShowTestsStudent(Statement stmt) throws SQLException {
        PgResultSet teachers = (PgResultSet) stmt.executeQuery(
                "SELECT tl.name, u.first_name, u.last_name, u.middle_name " +
                        "FROM public.schedule s " +
                        "JOIN public.test_list tl on s.test_id=tl.row_id " +
                        "JOIN public.group g on s.group_id=g.row_id " +
                        "JOIN public.user u on u.group_id=g.row_id " +
                        "WHERE u.role_id =1");
        System.out.println(formStringFromResultQuery(teachers));
    }

    private static void ShowTestResults(Statement stmt) throws SQLException {


        PgResultSet teachers = (PgResultSet) stmt.executeQuery(
                "SELECT u.first_name, tl.name, sum(q.score)\n" +
                        "FROM public.schedule s\n" +
                        "JOIN public.test_list tl on s.test_id=tl.row_id\n" +
                        "JOIN public.student_answer sa on sa.test_id =tl.row_id\n" +
                        "JOIN public.user u on sa.user_id = u.row_id\n" +
                        "JOIN public.answer a on a.row_id=sa.answer_id\n" +
                        "JOIN public.question q on q.row_id=a.question_id\n" +
                        "WHERE u.role_id = 1 and a.correct\n" +
                        "GROUP BY u.first_name, tl.name");
        System.out.println(formStringFromResultQuery(teachers));
    }

    public static void main(String[] args) throws SQLException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        var dbConnection = new DBConnection("tests", "postgres", "1234");
        var isConnected = dbConnection.Connect();
        if (!isConnected) {
            System.out.println("Ошибка чтения бд");
            return;
        }
        var dbManager = new DbManager(dbConnection);


        InsertUsers(dbManager);
        InsertSubjects(dbManager);
        InsertQuestions(dbManager);
        InsertAnswers(dbManager);
        InsertTests(dbManager);
        InsertTestQuestionRelations(dbManager);
        InsertSchedules(dbManager);
        InsertStudentAnswers(dbManager);

        var stmt = dbConnection.get_manager().createStatement();
        ShowTeachers(stmt);
        System.out.println();
        ShowStudents(stmt);
        ShowTestsStudent(stmt);
        ShowTestResults(stmt);
    }
}
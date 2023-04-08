package org.example;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DbManager {


    private DBConnection _dbConnection;


    private void insertToTable(String tableName, String columnNames, String columnValues) throws SQLException {
        var query = MessageFormat.format("INSERT INTO {0} ({1}) VALUES ({2})",
                tableName, columnNames, columnValues);
        _dbConnection.get_manager().createStatement().execute(query);
    }

    public DbManager(DBConnection dbConnection) {
        _dbConnection = dbConnection;
    }
    public void InsertAnswer(Integer question_id, String text, Boolean correct) throws SQLException {
        insertToTable("public.answer", "question_id, text, correct", MessageFormat.format("{0}, \'\'{1}\'\' , {2} ", question_id, text, correct));
    }



    public void InsertQuestion(String text, Integer score, Boolean active) throws SQLException {
        insertToTable("public.question", "text, score, active",
                MessageFormat.format("\'\'{0}\'\', {1}, {2}", text, score, active));
    }


    public void InsertSchedule(Integer test_id, Integer group_id, Integer duration, LocalDate start_dt, LocalTime start_time, LocalDate end_dt, LocalTime end_time, Boolean active) throws SQLException {
        var dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var timeformatter = DateTimeFormatter.ofPattern("HH:mm:SS");

        insertToTable("public.schedule", "test_id, group_id, duration, start_dt, start_time, end_dt, end_time, active",
                MessageFormat.format("{0}, {1}, {2}, \'\'{3}\'\', \'\'{4}\'\', \'\'{5}\'\',\'\'{6}\'\', {7}",
                        test_id, group_id, duration, start_dt.format(dateformatter), start_time.format(timeformatter), end_dt.format(dateformatter), end_time.format(timeformatter),
                        active));
    }
    public void InsertStudentAnswer(Integer test_id, Integer user_id, Integer answer_id) throws SQLException {
        insertToTable("public.student_answer", (" test_id, user_id, answer_id"),
                MessageFormat.format("{0},{1},{2}", test_id, user_id, answer_id));
    }

    public void InsertSubject(String name) throws SQLException {
        insertToTable("public.subject", "name", MessageFormat.format("\'\'{0}\'\'", name));
    }
    public void InsertTestQuestionRelation(Integer test_id, Integer question_id) throws SQLException {
        insertToTable("public.test", "test_id, question_id",
                MessageFormat.format("{0}, {1}", test_id, question_id));
    }
    public void InsertTestList(String name, Integer subject_id, Integer teacher_id) throws SQLException {
        insertToTable("public.test_list", "name, subject_id, teacher_id",
                MessageFormat.format("\'\'{0}\'\', {1}, {2}", name, subject_id, teacher_id));
    }
    public void InsertUser(String first_name, String last_name, String middle_name, String login, String pass_hash, Integer group_id, Integer role_id) throws SQLException {

        insertToTable("public.user", "first_name, last_name, " +
                        "middle_name, login, password_hash, group_id, role_id",
                MessageFormat.format("\'\'{0}\'\', \'\'{1}\'\', \'\'{2}\'\', \'\'{3}\'\', \'\'{4}\'\',{5}, {6}",
                        first_name, last_name, middle_name, login, pass_hash, group_id, role_id));
    }






}

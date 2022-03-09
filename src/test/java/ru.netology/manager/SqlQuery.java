package ru.netology.manager;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.data.AuthCode;
import ru.netology.data.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlQuery {

    public static User getUser() throws SQLException {
        var usersSQL = "SELECT * FROM users;";
        var runner = new QueryRunner();
        User user1;
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            user1 = runner.query(conn, usersSQL, new BeanHandler<>(User.class));

        }
        return user1;
    }

    public static String getAuthCodeFromUser() {
        String usersSQL = "SELECT * FROM auth_codes WHERE created = (SELECT MAX(created) FROM auth_codes);";
        var runner = new QueryRunner();
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            AuthCode code = runner.query(conn, usersSQL, new BeanHandler<>(AuthCode.class));
            return code.getCode();
        } catch (SQLException ignored) {
        }
        return "";
    }

    public static void clearTables() throws SQLException {
        var runner = new QueryRunner();
        String delUsersSQL = "DELETE FROM users;";
        String delCardsSQL = "DELETE FROM cards;";
        String delAuthSQL = "DELETE FROM auth_codes;";
        String delTransSQL = "DELETE FROM card_transactions;";

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            // очистка таблиц
            runner.update(conn, delTransSQL);
            runner.update(conn, delCardsSQL);
            runner.update(conn, delAuthSQL);
            runner.update(conn, delUsersSQL);
        }
    }
}



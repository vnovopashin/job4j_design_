package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        String url = this.properties.getProperty("url");
        String login = this.properties.getProperty("login");
        String pass = this.properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, pass);
        } catch (SQLException e) {
            throw new IllegalStateException("Не удалось установить соединение с БД", e);
        }
    }

    public void createTable(String tableName) {
        execute("CREATE TABLE IF NOT EXISTS " + tableName + " (id SERIAL PRIMARY KEY,"
                + "name TEXT)");
    }

    public void dropTable(String tableName) {
        execute("DROP TABLE IF EXISTS " + tableName);
    }

    public void addColumn(String tableName, String columnName, String type) {
        execute("ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type);
    }

    public void dropColumn(String tableName, String columnName) {
        execute("ALTER TABLE " + tableName + " DROP COLUMN " + columnName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        execute("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void execute(String sql) {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения SQL: " + sql, e);
        }
    }
}

package TwoDo.db;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private DatabaseCredentials credentials = new DatabaseCredentials();
    private Connection conn = null;
    private Statement statement;

    private void open() throws Exception {
        conn = DriverManager.getConnection(credentials.url, credentials.user, credentials.password);
        statement = conn.createStatement();
    }

    private void close() throws Exception {
        statement.close();
        conn.close();
    }

    public ArrayList<TaskData> getAllTasks() {
        ArrayList<TaskData> records = new ArrayList<TaskData>();
        try {
            open();
            ResultSet result = statement.executeQuery("SELECT * FROM tasks ORDER BY DueDate Asc");
            while (result.next()) {
                TaskData record = new TaskData();
                record.id = result.getInt("id");
                record.date = result.getString("DueDate");
                record.title = result.getString("Task");
                record.desc = result.getString("Details");
                record.user = result.getInt("User");
                records.add(record);
            }

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            try {
                close();
            } catch (Exception e) {
                System.out.println("Could not close connection");
            }
        }
        return records;
    }
}

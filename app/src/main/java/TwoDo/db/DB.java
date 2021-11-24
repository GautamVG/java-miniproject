package TwoDo.db;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private DatabaseCredentials credentials = new DatabaseCredentials();
    private Connection conn = null;
    private String fetch = "SELECT * FROM tasks ORDER BY DueDate Asc";
    private String insert = "INSERT INTO tasks (Task, Details, DueDate) VALUES (?, ?, ?)";
    private String update = "UPDATE tasks SET Task = ?, Details = ?, DueDate = ? WHERE id = ?";
    private String delete = "DELETE FROM tasks WHERE id = ?";

    private void open() throws Exception {
        conn = DriverManager.getConnection(credentials.url, credentials.user, credentials.password);
    }

    private void close() throws Exception {
        conn.close();
    }

    public ArrayList<TaskData> getAllTasks() {
        ArrayList<TaskData> records = new ArrayList<TaskData>();
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(fetch);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                TaskData record = new TaskData();
                record.id = result.getInt("id");
                record.title = result.getString("Task");
                record.desc = result.getString("Details");
                record.setDate(result.getTimestamp("DueDate"));
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

    public void insertNewTask(TaskData data) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(insert);
            statement.setString(1, data.title);
            statement.setString(2, data.desc);
            statement.setTimestamp(3, data.getDateAsTimestamp());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            try {
                close();
            } catch (Exception e) {
                System.out.println("Could not close connection");
            }
        }
    }

    public void updateTask(TaskData data) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(update);
            statement.setString(1, data.title);
            statement.setString(2, data.desc);
            statement.setTimestamp(3, data.getDateAsTimestamp());
            statement.setInt(4, data.id);
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            try {
                close();
            } catch (Exception e) {
                System.out.println("Could not close connection");
            }
        }
    }

    public void deleteTask(int id) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            try {
                close();
            } catch (Exception e) {
                System.out.println("Could not close connection");
            }
        }
    }
 
}
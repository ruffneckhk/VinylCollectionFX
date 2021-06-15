package sample;

import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;

public class MySQLite {

    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:vinyls.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:vinyls.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS vinyls (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	author text NOT NULL,\n"
                + "	tittle text NOT NULL,\n"
                + "	style text NOT NULL,\n"
                + "	state text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String author, String tittle, String style, String state) {
        String sql = "INSERT INTO vinyls(author, tittle, style, state) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement prepareStatement = conn.prepareStatement(sql)) {
            prepareStatement.setString(1, author);
            prepareStatement.setString(2, tittle);
            prepareStatement.setString(3, style);
            prepareStatement.setString(4, state);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> selectAll() {

        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT * FROM vinyls";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString("id") + ". " + rs.getString("author") + " - " +
                        rs.getString("tittle") + "\n" + rs.getString("style") + "\n" + rs.getString("state"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void update(int id, String name) {
        String sql = "UPDATE vinyls SET name = ? "
                + "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM vinyls WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            // set the corresponding param
            preparedStatement.setInt(1, id);
            // execute the delete statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

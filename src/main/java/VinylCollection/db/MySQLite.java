package VinylCollection.db;

import VinylCollection.dao.Vinyl;

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

        return getStrings(list, sql);
    }

    public ArrayList<String> selectAllByState(String selectedState) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM vinyls WHERE state = " + selectedState;

        return getStrings(list, sql);
    }

    public ArrayList<String> selectAllByAuthor(String selectedAuthor) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM vinyls WHERE author = " + selectedAuthor;

        return getStrings(list, sql);
    }

    public ArrayList<String> selectAllByStyle(String selectedStyle) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM vinyls WHERE style = " + selectedStyle;

        return getStrings(list, sql);
    }

    public Vinyl selectById(int id) {

        String sql = "SELECT * FROM vinyls WHERE id = " + id;
        String author = "";
        String tittle = "";
        String style = "";
        String state = "";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                author = rs.getString("author");
                tittle = rs.getString("tittle");
                style = rs.getString("style");
                state = rs.getString("state");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Vinyl(author, tittle, style, state);
    }

    private ArrayList<String> getStrings(ArrayList<String> list, String sql) {
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

    public ArrayList<String> selectAllAuthors() {

        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT author FROM vinyls GROUP BY author";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString("author"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<String> selectAllTittles() {

        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT tittle FROM vinyls";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString("tittle"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<String> selectAllStyles() {

        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT style FROM vinyls GROUP BY style";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString("style"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<String> selectAllStates() {

        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT state FROM vinyls GROUP BY state";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString("state"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(int id, String author, String tittle, String style, String state) {

        String sql = "UPDATE vinyls SET author = ? , "
                + "tittle = ? ,"
                + "style = ? ,"
                + " state = ? " +
                "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            stmt.setString(1, author);
            stmt.setString(2, tittle);
            stmt.setString(3, style);
            stmt.setString(4, state);
            stmt.setInt(5, id);
            // update
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
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

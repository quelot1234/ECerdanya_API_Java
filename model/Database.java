package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection con;
    private final String HOST;
    private final String USER;
    private final String PASSWORD;
    private final String DATABASE;
    private final String DSN;

    public Database() {
        this.HOST = "localhost";
        this.USER = "root";
        this.PASSWORD = "";
        this.DATABASE = "e_cerdanya";
        this.DSN = "jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?serverTimezone=UTC";
    }

    public Connection getConnection() throws SQLException {
        if (this.con == null || this.con.isClosed()) {
            this.con = DriverManager.getConnection(this.DSN, this.USER, this.PASSWORD);
        }

        return this.con;
    }

    public void closeConnection() throws SQLException {
        if (!this.con.isClosed()) {
            this.con.close();
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

import java.sql.*;

/**
 *
 * @author daniel.legrand
 */
public class ChessDB {
    ChessDBManager dbManager;
    Connection conn;
    Statement statement;

    public ChessDB() {
        dbManager = new ChessDBManager();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}

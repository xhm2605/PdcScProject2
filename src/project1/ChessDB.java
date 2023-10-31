/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void main(String[] args) {
        
    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}

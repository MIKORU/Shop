package com.alice.shop.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionSQL {
	public static void main(String[] arg) {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?user=root&password=27185306&useUnicode=true&characterEncoding=UTF8");
		    Statement s =  conn.createStatement();
		    System.out.println(conn.isClosed());
		}
		 catch (Exception e) {
		            e.printStackTrace();
		}
	}
}

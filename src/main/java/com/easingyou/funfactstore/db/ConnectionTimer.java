package com.easingyou.funfactstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionTimer {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5445/funfactstore";
		String user = "ffsuser";
		String password = "secret";

		try {
			long start = System.currentTimeMillis();
			Connection conn = DriverManager.getConnection(url, user, password);
			long durationMs = (System.currentTimeMillis() - start);
			System.out.println("Connection established in " + durationMs + " ms");

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

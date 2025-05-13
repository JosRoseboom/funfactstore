package com.easingyou.dbtimer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LongSummaryStatistics;
import java.util.stream.LongStream;

class ConnectionTimer {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5445/funfactstore";
		String user = "ffsuser";
		String password = "secret";

		final LongSummaryStatistics connectionStats = LongStream.range(0, 110)
				.map(i -> timeConnection(url, user, password))
				.skip(10)
				.summaryStatistics();

		System.out.println(connectionStats);
	}

	private static long timeConnection(String url, String user, String password) {
		try {
			long start = System.currentTimeMillis();
			Connection conn = DriverManager.getConnection(url, user, password);
			long durationMs = (System.currentTimeMillis() - start);
			System.out.println("Connection established in " + durationMs + " ms");

			conn.close();
			return durationMs;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1L;
		}
	}
}

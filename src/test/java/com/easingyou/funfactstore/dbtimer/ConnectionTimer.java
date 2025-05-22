package com.easingyou.funfactstore.dbtimer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LongSummaryStatistics;
import java.util.stream.LongStream;

abstract class ConnectionTimer {

	void measure(){

		final LongSummaryStatistics connectionStats = LongStream.range(0, 1025)
				.map(i -> timeConnection())
				.skip(25)
				.summaryStatistics();

		System.out.println(connectionStats);
	}

	private long timeConnection() {
		try {
			long start = System.currentTimeMillis();
			Connection conn = getConnection();
			long durationMs = (System.currentTimeMillis() - start);

			conn.close();
			return durationMs;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1L;
		}
	}

	protected abstract Connection getConnection() throws SQLException;
}

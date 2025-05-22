package com.easingyou.funfactstore.dbtimer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LongSummaryStatistics;
import java.util.stream.LongStream;

abstract class ConnectionTimer {

	void measure(){

		final LongSummaryStatistics stats = LongStream.range(0, 1025)
				.map(i -> timeConnectionInNanos())
				.skip(25)
				.summaryStatistics();

		System.out.println("count=" + stats.getCount());
		System.out.println("sum=" + asMs(stats.getSum()));
		System.out.println("min=" + asMs(stats.getMin()));
		System.out.println("average=" + formatMs(stats.getAverage() / 1_000_000.0));
		System.out.println("max=" + asMs(stats.getMax()));
	}

	private String asMs(long nanos){
		return formatMs(nanos / 1_000_000.0);
	}

	private String formatMs(double ms){
		return String.format("%.6f ms", ms);
	}

	private long timeConnectionInNanos() {
		try {
			long start = System.nanoTime();
			Connection conn = getConnection();
			long durationMs = (System.nanoTime() - start);

			conn.close();
			return durationMs;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1L;
		}
	}

	protected abstract Connection getConnection() throws SQLException;
}

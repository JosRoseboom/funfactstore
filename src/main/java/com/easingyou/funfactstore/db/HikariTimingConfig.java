package com.easingyou.funfactstore.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.metrics.IMetricsTracker;

//@Configuration
public class HikariTimingConfig {
	private final Logger log = LoggerFactory.getLogger(HikariTimingConfig.class);

	public HikariTimingConfig(HikariDataSource ds) {
		ds.setMetricsTrackerFactory((poolName, poolStats) -> new IMetricsTracker() {

			@Override
			public void recordConnectionAcquiredNanos(long elapsedAcquiredNanos) {
				log.info("[{}] Connection acquired in {} ms ({} ns)",	poolName, elapsedAcquiredNanos / 1_000_000, elapsedAcquiredNanos);
			}

			@Override
			public void recordConnectionUsageMillis(long elapsedBorrowedMillis) {
				log.info("[{}] Connection used for {} ms",	poolName, elapsedBorrowedMillis);
			}

		});
	}
}

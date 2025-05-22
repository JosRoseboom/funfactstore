package com.easingyou.funfactstore.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.metrics.IMetricsTracker;

@Configuration
public class HikariTimingConfig {
	private final Logger log = LoggerFactory.getLogger(HikariTimingConfig.class);

	public HikariTimingConfig(HikariDataSource ds) {
		ds.setMetricsTrackerFactory((poolName, poolStats) -> new IMetricsTracker() {

			@Override
			public void recordConnectionAcquiredNanos(long elapsedAcquiredNanos) {
				log.debug("[{}] Connection acquired",	poolName);
			}

			@Override
			public void recordConnectionUsageMillis(long elapsedBorrowedMillis) {
				log.debug("[{}] Connection used for {} ms",	poolName, elapsedBorrowedMillis);
			}

		});
	}
}

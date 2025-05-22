package com.easingyou.funfactstore.dbtimer;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.easingyou.funfactstore.TestcontainersConfiguration;
import com.zaxxer.hikari.HikariDataSource;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PoolConnectionTimerTest {

	@Autowired
	private HikariDataSource dataSource;

	@Test
	void timePoolConnections() throws SQLException {
		ConnectionTimer noPoolTimer = new ConnectionTimer() {
			@Override
			protected Connection getConnection() throws SQLException {
				return dataSource.getConnection();
			}
		};

		noPoolTimer.measure();
	}

}

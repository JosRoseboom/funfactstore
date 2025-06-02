package com.easingyou.funfactstore.dbtimer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.easingyou.funfactstore.TestcontainersConfiguration;
import com.zaxxer.hikari.HikariDataSource;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ConnectionTimerTest {

	@Autowired
	private HikariDataSource dataSource;

	@Test
	void timePoolConnections() throws SQLException {

		ConnectionTimer poolConnectionTimer = new ConnectionTimer() {
			@Override
			protected Connection getConnection() throws SQLException {
				return dataSource.getConnection();
			}
		};

		System.out.println("\nPool (Hikari) connections stats:");
		poolConnectionTimer.measure();
		System.out.println();
	}

	@Test
	void timeNoPoolConnections() {
		final String url = dataSource.getJdbcUrl();
		final String user = dataSource.getUsername();
		final String password = dataSource.getPassword();

		ConnectionTimer noPoolTimer = new ConnectionTimer() {
			@Override
			protected Connection getConnection() throws SQLException {
				return DriverManager.getConnection(url, user, password);
			}
		};

		System.out.println("\nNo pool (DriverManager) connections stats:");
		noPoolTimer.measure();
		System.out.println();
	}

}

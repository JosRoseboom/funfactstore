package com.easingyou.funfactstore.dbtimer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class NoPoolConnectionTimerTest {

	@Test
	void timeNoPoolConnections() {
		final String url = "jdbc:postgresql://localhost:5445/funfactstore";
		final String user = "ffsuser";
		final String password = "secret";

		ConnectionTimer noPoolTimer = new ConnectionTimer() {
			@Override
			protected Connection getConnection() throws SQLException {
				return DriverManager.getConnection(url, user, password);
			}
		};

		noPoolTimer.measure();
	}
}

package com.nevexis.connection_pool;

public class ConnectionPoolCreator {

	private static ConnectionPool connectionPool = null;

	public synchronized static ConnectionPool getInstance() {

		if (connectionPool == null) {
			connectionPool = new ConnectionPoolImpl();
		}

		return connectionPool;
	}

}

package com.nevexis.connection_pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
	public final static int POOL_SIZE = 10;

	private static final String CONNECTION_ADR = "jdbc:mysql://localhost:3306/people";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";

	private Stack<Connection> connectionPool = new Stack<>();

	ConnectionPoolImpl() {
		for (int i = 0; i < POOL_SIZE; i++) {
			try {
				connectionPool.add(DriverManager.getConnection(CONNECTION_ADR, USERNAME, PASSWORD));
			} catch (SQLException e) {
				throw new Error("Fatal:", e);
			}
		}
	}

	@Override
	synchronized public Connection getConnection() {
		return connectionPool.pop();
	}

	@Override
	synchronized public Connection getConnection2() {
		Connection cnn = connectionPool.pop();

		Handler handler = new Handler(cnn, this);

		return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[] { Connection.class },
				handler);
	}

	@Override
	synchronized public void releaseConnection(Connection connection) {
		connectionPool.push(connection);
	}

	static class Handler implements InvocationHandler {

		private final Connection original;
		private ConnectionPool pool;

		public Handler(Connection original, ConnectionPool pool) {
			this.original = original;
			this.pool = pool;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			if ("close".equals(method.getName())) {
				try {
					if (!((Connection) proxy).getAutoCommit()) {
						((Connection) proxy).rollback();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pool.releaseConnection((Connection) proxy);
				return null;
			}

			return method.invoke(original, args);
		}

	}

}

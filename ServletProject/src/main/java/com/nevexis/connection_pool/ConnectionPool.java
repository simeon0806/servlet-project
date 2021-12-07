package com.nevexis.connection_pool;

import java.sql.Connection;

public interface ConnectionPool {

	Connection getConnection();

	void releaseConnection(Connection connection);

	Connection getConnection2();

}

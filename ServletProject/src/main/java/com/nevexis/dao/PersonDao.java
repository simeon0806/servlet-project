package com.nevexis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nevexis.connection_pool.ConnectionPool;
import com.nevexis.connection_pool.ConnectionPoolCreator;
import com.nevexis.model.Person;

public class PersonDao {

	static ConnectionPool connectionPool = ConnectionPoolCreator.getInstance();

	public static List<Person> getPersons() throws SQLException {

		try (Connection connection = connectionPool.getConnection2();
				PreparedStatement statement = connection.prepareStatement("SELECT id,name FROM person LIMIT 100");
				ResultSet rs = statement.executeQuery()) {
			List<Person> list = new ArrayList<>(100);
			while (rs.next()) {
				list.add(new Person(rs.getLong("id"), rs.getString("name")));
			}
			return list;
		}

	}

	public static void insertPersons(Person person) throws SQLException {

		try (Connection connection = connectionPool.getConnection2();
				PreparedStatement statement = connection
						.prepareStatement("insert into person(id, `name`) value (?, ?)")) {
			statement.setLong(1, person.getId());
			statement.setString(2, person.getName());

			statement.execute();
		} // try

	} // insertPersons

	public static void deletePerson(Long id) throws SQLException {

		try (Connection connection = connectionPool.getConnection2();
				PreparedStatement ps = connection.prepareStatement("DELETE FROM person WHERE id = " + id)) {
			ps.execute();
		}

	}

}

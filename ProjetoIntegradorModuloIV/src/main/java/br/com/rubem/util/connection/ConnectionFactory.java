package br.com.rubem.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/projetointegrador";
	private static final String USER = "postgres";
	private static final String PASSWORD = "toor";

	public Connection open() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro ao abrir a conexão: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void close(ResultSet r, Statement s, Connection c) {
		try {
			if (r != null)
				r.close();
			if (s != null)
				s.close();
			if (c != null) {
				c.close();
				c = null;
			}

		} catch (Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro ao fechar a conexão: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

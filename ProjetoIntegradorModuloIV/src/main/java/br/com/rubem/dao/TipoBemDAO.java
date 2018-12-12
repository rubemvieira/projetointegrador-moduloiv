package br.com.rubem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.rubem.model.TipoBem;
import br.com.rubem.util.connection.ConnectionFactory;

public class TipoBemDAO extends ConnectionFactory {

/* CRUD 
 * C - Create
 * R - Retrieve
 * U - Update
 * D - Delete
 * */

	public List<TipoBem> retrieveAll() {
		Connection conexao = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "SELECT id, descricao, vida_util, depreciavel FROM tipo_bem ORDER BY descricao";
		List<TipoBem> lstTipoBem = null;
		try {
			conexao = open();
			psSelect = conexao.prepareStatement(sql);
			rsSelect = psSelect.executeQuery();
			lstTipoBem = new ArrayList<>();
			while (rsSelect.next()) {
				TipoBem c = new TipoBem();
				c.setId(rsSelect.getInt("id"));
				c.setDescricao(rsSelect.getString("descricao"));
				c.setVida_Util(rsSelect.getInt("vida_util"));
				c.setDepreciavel(rsSelect.getBoolean("depreciavel"));
				lstTipoBem.add(c);
			}
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no retrieve: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(rsSelect, psSelect, conexao);
		}
		return lstTipoBem;
	}

	public TipoBem retrieveById(Long id) {
		Connection conexao = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "SELECT id, descricao, vida_util, depreciavel FROM tipo_bem WHERE id = ?";
		TipoBem c = null;
		try {
			conexao = open();
			psSelect = conexao.prepareStatement(sql);
			psSelect.setLong(1, id);
			rsSelect = psSelect.executeQuery();
			if (rsSelect.next()) {
				c = new TipoBem();
				c.setId(rsSelect.getInt("id"));
				c.setDescricao(rsSelect.getString("descricao"));
				c.setVida_Util(rsSelect.getInt("vida_util"));
				c.setDepreciavel(rsSelect.getBoolean("depreciavel"));
			}
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no retrieve: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(rsSelect, psSelect, conexao);
		}
		return c;
	}	
	
	public void update(TipoBem tipobem) {
		Connection conexao = null;
		PreparedStatement psUpdate = null;
		String sql = "UPDATE tipo_bem SET descricao = ?, vida_util = ?, depreciavel = ? WHERE id = ?";
		
		try {
			conexao = open();
			psUpdate = conexao.prepareStatement(sql);
			psUpdate.setString(1, tipobem.getDescricao());
			psUpdate.setLong(2, tipobem.getVida_Util());
			psUpdate.setBoolean(3, tipobem.isDepreciavel());
			psUpdate.setLong(4, tipobem.getId());
			psUpdate.executeUpdate();
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no update: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(null, psUpdate, conexao);
		}
	}
	
	public void insert(TipoBem tipobem) {
		Connection conexao = null;
		PreparedStatement psInsert = null;
		String sql = "INSERT INTO tipo_bem (descricao, vida_util, depreciavel) VALUES (?,?,?)";
		
		try {
			conexao = open();
			psInsert = conexao.prepareStatement(sql);
			psInsert.setString(1, tipobem.getDescricao());
			psInsert.setLong(2, tipobem.getVida_Util());
			psInsert.setBoolean(3, tipobem.isDepreciavel());
			psInsert.executeUpdate();
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no insert: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(null, psInsert, conexao);
		}
	}	
	
	public void delete(TipoBem tipobem) {
		Connection conexao = null;
		PreparedStatement psDelete = null;
		String sql = "DELETE FROM tipo_bem WHERE id = ?";
		
		try {
			conexao = open();
			psDelete = conexao.prepareStatement(sql);
			psDelete.setLong(1, tipobem.getId());
			psDelete.executeUpdate();
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no delete: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(null, psDelete, conexao);
		}
	}	
	
}

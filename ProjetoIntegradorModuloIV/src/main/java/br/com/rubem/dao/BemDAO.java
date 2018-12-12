package br.com.rubem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.rubem.model.Bem;
import br.com.rubem.model.TipoBem;
import br.com.rubem.util.connection.ConnectionFactory;

public class BemDAO extends ConnectionFactory {

/* CRUD 
 * C - Create
 * R - Retrieve
 * U - Update
 * D - Delete
 * */

	public List<Bem> retrieveAll() {
		Connection conexao = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "" + 
				" select " + 
				" 	b.id, " +
				" 	t.id as tipo, " + 				
				" 	t.descricao as tipo_descricao, " + 
				" 	b.descricao, " + 
				" 	b.data_aquisicao, " + 
				" 	b.valor_aquisicao," + 
				" 	b.vida_util," + 
				" 	b.turno_trabalho," + 
				" 	b.valor_residual," + 
				" 	b.valor_residual_tipo," + 
				" 	b.data_venda," + 
				" 	b.valor_venda," +
				" 	b.adquirido_usado," +
				" 	b.tempo_uso, " +
				" 	b.tempo_uso_tipo " +
				" from" + 
				" 	bem b inner join tipo_bem t on t.id = b.tipo" +
				" where b.data_venda is null";
		List<Bem> lstBem = null;
		try {
			conexao = open();
			psSelect = conexao.prepareStatement(sql);
			rsSelect = psSelect.executeQuery();
			lstBem = new ArrayList<>();
			while (rsSelect.next()) {
				TipoBem t = new TipoBem();
				t.setId(rsSelect.getInt("tipo"));
				t.setDescricao(rsSelect.getString("tipo_descricao"));
				Bem c = new Bem();
				c.setTipo(t);
				c.setId(rsSelect.getInt("id"));
				c.setDescricao(rsSelect.getString("descricao"));
				c.setVida_Util(rsSelect.getInt("vida_util"));
				c.setData_aquisicao(rsSelect.getDate("data_aquisicao"));
				c.setValor_aquisicao(rsSelect.getDouble("valor_aquisicao"));
				c.setTurno_trabalho(rsSelect.getDouble("turno_trabalho"));
				c.setValor_residual(rsSelect.getDouble("valor_residual"));
				c.setValor_residual_tipo(rsSelect.getString("valor_residual_tipo").charAt(0));
				c.setData_venda(rsSelect.getDate("data_venda"));
				c.setValor_venda(rsSelect.getDouble("valor_venda"));
				c.setUsado(rsSelect.getBoolean("adquirido_usado"));
				c.setTempo_uso(rsSelect.getDouble("tempo_uso"));
				if(rsSelect.getString("tempo_uso_tipo") != null) {
					c.setTipo_tempo_uso(rsSelect.getString("tempo_uso_tipo").charAt(0));
				} else {
					c.setTipo_tempo_uso(String.valueOf("A").charAt(0));
				}
				lstBem.add(c);
			}
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no retrieve: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(rsSelect, psSelect, conexao);
		}
		return lstBem;
	}

	public List<Bem> retrieveBaixadoAll() {
		Connection conexao = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "" + 
				" select " + 
				" 	b.id, " +
				" 	t.id as tipo, " + 				
				" 	t.descricao as tipo_descricao, " + 
				" 	b.descricao, " + 
				" 	b.data_aquisicao, " + 
				" 	b.valor_aquisicao," + 
				" 	b.vida_util," + 
				" 	b.turno_trabalho," + 
				" 	b.valor_residual," + 
				" 	b.valor_residual_tipo," + 
				" 	b.data_venda," + 
				" 	b.valor_venda," + 
				" 	b.adquirido_usado, " +
				" 	b.tempo_uso, " +
				" 	b.tempo_uso_tipo " +
				" from" + 
				" 	bem b inner join tipo_bem t on t.id = b.tipo" + 
				"  where not b.data_venda is null ";
		List<Bem> lstBem = null;
		try {
			conexao = open();
			psSelect = conexao.prepareStatement(sql);
			rsSelect = psSelect.executeQuery();
			lstBem = new ArrayList<>();
			while (rsSelect.next()) {
				TipoBem t = new TipoBem();
				t.setId(rsSelect.getInt("tipo"));
				t.setDescricao(rsSelect.getString("tipo_descricao"));
				Bem c = new Bem();
				c.setTipo(t);
				c.setId(rsSelect.getInt("id"));
				c.setDescricao(rsSelect.getString("descricao"));
				c.setVida_Util(rsSelect.getInt("vida_util"));
				c.setData_aquisicao(rsSelect.getDate("data_aquisicao"));
				c.setValor_aquisicao(rsSelect.getDouble("valor_aquisicao"));
				c.setTurno_trabalho(rsSelect.getDouble("turno_trabalho"));
				c.setValor_residual(rsSelect.getDouble("valor_residual"));
				c.setValor_residual_tipo(rsSelect.getString("valor_residual_tipo").charAt(0));
				c.setData_venda(rsSelect.getDate("data_venda"));
				c.setValor_venda(rsSelect.getDouble("valor_venda"));
				c.setUsado(rsSelect.getBoolean("adquirido_usado"));
				c.setTempo_uso(rsSelect.getDouble("tempo_uso"));
				if(rsSelect.getString("tempo_uso_tipo") != null) {
					c.setTipo_tempo_uso(rsSelect.getString("tempo_uso_tipo").charAt(0));
				} else {
					c.setTipo_tempo_uso(String.valueOf("A").charAt(0));
				}
				lstBem.add(c);
			}
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no retrieve: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(rsSelect, psSelect, conexao);
		}
		return lstBem;
	}	

	public Bem retrieveById(Integer id) {
		Connection conexao = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "" + 
				" select " + 
				" 	b.id, " +
				" 	t.id as tipo, " + 				
				" 	t.descricao as tipo_descricao, " + 
				" 	b.descricao, " + 
				" 	b.data_aquisicao, " + 
				" 	b.valor_aquisicao," + 
				" 	b.vida_util," + 
				" 	b.turno_trabalho," + 
				" 	b.valor_residual," + 
				" 	b.valor_residual_tipo," + 
				" 	b.data_venda," + 
				" 	b.valor_venda," + 
				" 	b.adquirido_usado," +
				" 	b.tempo_uso," +
				" 	b.tempo_uso_tipo" +
				" from" + 
				" 	bem b inner join tipo_bem t on t.id = b.tipo" +
				" where b.id = ? ";
		Bem c = null;
		try {
			conexao = open();
			psSelect = conexao.prepareStatement(sql);
			psSelect.setLong(1, id);
			rsSelect = psSelect.executeQuery();
			if (rsSelect.next()) {
				TipoBem t = new TipoBem();
				t.setId(rsSelect.getInt("tipo"));
				t.setDescricao(rsSelect.getString("tipo_descricao"));
				c = new Bem();
				c.setTipo(t);
				c.setId(rsSelect.getInt("id"));
				c.setDescricao(rsSelect.getString("descricao"));
				c.setVida_Util(rsSelect.getInt("vida_util"));
				c.setData_aquisicao(rsSelect.getDate("data_aquisicao"));
				c.setValor_aquisicao(rsSelect.getDouble("valor_aquisicao"));
				c.setTurno_trabalho(rsSelect.getDouble("turno_trabalho"));
				c.setValor_residual(rsSelect.getDouble("valor_residual"));
				c.setValor_residual_tipo(rsSelect.getString("valor_residual_tipo").charAt(0));
				c.setData_venda(rsSelect.getDate("data_venda"));
				c.setValor_venda(rsSelect.getDouble("valor_venda"));
				c.setUsado(rsSelect.getBoolean("adquirido_usado"));
				c.setTempo_uso(rsSelect.getDouble("tempo_uso"));
				c.setTipo_tempo_uso(rsSelect.getString("tempo_uso_tipo").charAt(0));
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
	
	public void update(Bem bem) {
		Connection conexao = null;
		PreparedStatement psUpdate = null;
		String sql = "" + 
				" UPDATE bem SET " + 
				" 	tipo = ?, " + 				 
				" 	descricao = ?, " + 
				" 	data_aquisicao = ?, " + 
				" 	valor_aquisicao = ?, " + 
				" 	vida_util = ?, " + 
				" 	turno_trabalho = ?, " + 
				" 	valor_residual = ?, " + 
				" 	valor_residual_tipo = ?," + 
				" 	data_venda = ?," + 
				" 	valor_venda = ?," + 
				" 	adquirido_usado = ?," +
				" 	tempo_uso = ?," +
				" 	tempo_uso_tipo = ?" +
				" where id = ? ";		
		try {
			conexao = open();
			psUpdate = conexao.prepareStatement(sql);
			psUpdate.setLong(1, bem.getTipo().getId());
			psUpdate.setString(2, bem.getDescricao());
			psUpdate.setDate(3, new java.sql.Date (bem.getData_aquisicao().getTime()));
			psUpdate.setDouble(4, bem.getValor_aquisicao());
			psUpdate.setLong(5, bem.getVida_Util());
			psUpdate.setDouble(6, bem.getTurno_trabalho());
			psUpdate.setDouble(7, bem.getValor_residual());
			psUpdate.setString(8, String.valueOf(bem.getValor_residual_tipo()));
			
			if(bem.getData_venda() != null) {
				psUpdate.setDate(9, new java.sql.Date (bem.getData_venda().getTime()));
				psUpdate.setDouble(10, bem.getValor_venda());
			} else {
				psUpdate.setNull(9, 0);
				psUpdate.setNull(10, 0);
			}
			
			if(bem.getTempo_uso() != null) {
				psUpdate.setBoolean(11, bem.isUsado());
				psUpdate.setDouble(12, bem.getTempo_uso());
				psUpdate.setString(13, String.valueOf(bem.getTipo_tempo_uso()));
			} else {
				psUpdate.setBoolean(11, false);
				psUpdate.setNull(12, 0);
				psUpdate.setNull(13, 0);
			}			
			psUpdate.setLong(14, bem.getId());
			psUpdate.executeUpdate();
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no update: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(null, psUpdate, conexao);
		}
	}
	
	public void insert(Bem bem) {
		Connection conexao = null;
		PreparedStatement psInsert = null;
		String sql = "" + 
				" INSERT INTO bem ( " + 
				" 	tipo, " + 				 
				" 	descricao, " + 
				" 	data_aquisicao, " + 
				" 	valor_aquisicao, " + 
				" 	vida_util, " + 
				" 	turno_trabalho, " + 
				" 	valor_residual, " + 
				" 	valor_residual_tipo," + 
				" 	data_venda," + 
				" 	valor_venda," + 
				" 	adquirido_usado," +
				" 	tempo_uso," +
				" 	tempo_uso_tipo" +
				" ) values(?,?,?,?,?,?,?,?,?,?,?,?,? ) ";
		
		try {
			conexao = open();
			psInsert = conexao.prepareStatement(sql);
			psInsert.setLong(1, bem.getTipo().getId());
			psInsert.setString(2, bem.getDescricao());
			psInsert.setDate(3, new java.sql.Date(bem.getData_aquisicao().getTime()));
			psInsert.setDouble(4, bem.getValor_aquisicao());
			psInsert.setLong(5, bem.getVida_Util());
			psInsert.setDouble(6, bem.getTurno_trabalho());
			psInsert.setDouble(7, bem.getValor_residual());
			psInsert.setString(8, String.valueOf(bem.getValor_residual_tipo()));

			if(bem.getData_venda() != null) {
				psInsert.setDate(9, new java.sql.Date(bem.getData_venda().getTime()));
				psInsert.setDouble(10, bem.getValor_venda());
			} else {
				psInsert.setNull(9, 0);
				psInsert.setNull(10, 0);
			}			
			
			if(bem.getTempo_uso() != null) {
				psInsert.setBoolean(11, bem.isUsado());
				psInsert.setDouble(12, bem.getTempo_uso());
				psInsert.setString(13, String.valueOf(bem.getTipo_tempo_uso()));
			} else {
				psInsert.setBoolean(11, false);
				psInsert.setNull(12, 0);
				psInsert.setNull(13, 0);
			}

			psInsert.executeUpdate();
		}catch(Exception e) {
			System.err.println("-------------------------------------");
			System.err.println("Erro no insert: " + e.getMessage());
			e.printStackTrace();
		}finally {
			close(null, psInsert, conexao);
		}
	}	
	
	public void delete(Bem bem) {
		Connection conexao = null;
		PreparedStatement psDelete = null;
		String sql = "DELETE FROM bem WHERE id = ?";
		
		try {
			conexao = open();
			psDelete = conexao.prepareStatement(sql);
			psDelete.setLong(1, bem.getId());
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

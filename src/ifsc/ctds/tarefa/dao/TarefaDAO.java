package ifsc.ctds.tarefa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import ifsc.ctds.tarefa.entity.Tarefa;

public class TarefaDAO implements DAO<Tarefa> {

	@Override
	public Tarefa get(Long idTarefa) {
		Tarefa tarefa = null;
		String sql = "SELECT * FROM tarefas WHERE idTarefa = ?";
		// Conexão com o banco de dados
		Connection conexao = null;
		// Preparação de um comando SQL
		PreparedStatement stm = null;
		// Guarda o retorno da operação SQL
		ResultSet rset = null;
		try {
			// Obtém conexão com o banco
			conexao = Conexao.getConnection();
			// Preparar o comando e seus parâmetros
			stm = (PreparedStatement) conexao.prepareStatement(sql);
			stm.setLong(1, idTarefa);
			// Executa a consulta e obtém as respostas
			rset = stm.executeQuery();
			while (rset.next()) {
				tarefa = new Tarefa();
				// Atribui os valores lidos ao objeto
				tarefa.setIdTarefa(rset.getInt("idTarefa"));
				tarefa.setTarefa(rset.getString("tarefa"));
				tarefa.setDescricao(rset.getString("descricao"));
				tarefa.setDia(rset.getString("dia"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados. Error: " + e);
		} finally {
			// Fecha todas as conexões após o seu uso
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tarefa;
	}

	@Override
	public List<Tarefa> getAll() {
		List<Tarefa> tarefas = new ArrayList<>();
		String sql = "SELECT * FROM tarefas";
		// Conexão com o banco de dados
		Connection conexao = null;
		// Preparação de um comando SQL
		PreparedStatement stm = null;
		// Guarda o retorno da operação SQL
		ResultSet rset = null;
		try {
			// Obtém conexão com o banco
			conexao = Conexao.getConnection();
			// Preparar o comando e seus parâmetros
			stm = (PreparedStatement) conexao.prepareStatement(sql);
			// Executa a consulta e obtém as respostas
			rset = stm.executeQuery();
			while (rset.next()) {
				Tarefa tarefa = new Tarefa();
				// Atribui os valores lidos ao objeto
				tarefa.setIdTarefa(rset.getInt("idTarefa"));
				tarefa.setTarefa(rset.getString("tarefa"));
				tarefa.setDescricao(rset.getString("descricao"));
				tarefa.setDia(rset.getString("dia"));
				// Adiciona a lista de tarefas o novo objeto
				tarefas.add(tarefa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados. Error: " + e);
		} finally {
			// Fecha todas as conexões após o seu uso
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tarefas;
	}

	@Override
	public int create(Tarefa tarefa) {
		String sql = "INSERT INTO tarefas (tarefa, descricao, dia) VALUES (?, ?, ?)";
		// Conexão com o banco de dados
		Connection conexao = null;
		// Preparação de um comando SQL
		PreparedStatement stm = null;
		try {
			// Obtém conexão com o banco
			conexao = Conexao.getConnection();
			// Preparar o comando e seus parâmetros
			stm = (PreparedStatement) conexao.prepareStatement(sql);
			stm.setString(1, tarefa.getTarefa());
			stm.setString(2, tarefa.getDescricao());
			stm.setString(3, tarefa.getDia());
			// Executa a operação
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados. Error: " + e);
		} finally {
			// Fecha todas as conexões após o seu uso
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public boolean update(Tarefa tarefa, String[] params) {
		String sql = "UPDATE tarefas SET tarefa = ?, descricao = ?, dia = ? WHERE idTarefa = ?";
		// Conexão com o banco de dados
		Connection conexao = null;
		// Preparação de um comando SQL
		PreparedStatement stm = null;
		try {
			// Obtém conexão com o banco
			conexao = Conexao.getConnection();
			// Preparar o comando e seus parâmetros
			stm = (PreparedStatement) conexao.prepareStatement(sql);
			stm.setString(1, tarefa.getTarefa());
			stm.setString(2, tarefa.getDescricao());
			stm.setString(3, tarefa.getDia());
			stm.setLong(4, tarefa.getIdTarefa());
			// Executa a operação
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados. Error: " + e);
		} finally {
			// Fecha todas as conexões após o seu uso
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Tarefa tarefa) {
		String sql = "DELETE FROM tarefas WHERE idTarefa = ?";
		// Conexão com o banco de dados
		Connection conexao = null;
		// Preparação de um comando SQL
		PreparedStatement stm = null;
		try {
			// Obtém conexão com o banco
			conexao = Conexao.getConnection();
			// Preparar o comando e seus parâmetros
			stm = (PreparedStatement) conexao.prepareStatement(sql);
			stm.setLong(1, tarefa.getIdTarefa());
			// Executa a operação
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados. Error: " + e);
		} finally {
			// Fecha todas as conexões após o seu uso
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TarefaDAO dao = new TarefaDAO();
		// Teste de criação
		Tarefa nova = new Tarefa();
		nova.setTarefa("Estudar");
		nova.setDescricao("Estudar Java");
		nova.setDia("27/02/2024");
		dao.create(nova);

		nova.setTarefa("Trabalhar");
		nova.setDescricao("Trabalhar em projetos");
		nova.setDia("06/04/2024");
		dao.create(nova);

		nova.setTarefa("Ler");
		nova.setDescricao("Ler um livro");
		nova.setDia("12/05/2024");
		dao.create(nova);
		// Teste de consulta específica
		Long id = 5L;
		Tarefa busca = dao.get(id);
		if (busca != null) {
			System.out.println("Tarefa encontrada: " + busca.getTarefa());
			busca.setTarefa("Exercícios");
			dao.update(busca, null);
		}
		// Teste de consulta geral
		List<Tarefa> lista = dao.getAll();
		System.out.println("Lista de tarefas:");
		for (Tarefa obj : lista) {
			System.out.println(obj.getTarefa());
			System.out.println(obj.getDescricao());
			System.out.println("Dia: " + obj.getDia());
			;
		}
		// Teste de exclusão
		// dao.delete(lista.get(0));
	}
}
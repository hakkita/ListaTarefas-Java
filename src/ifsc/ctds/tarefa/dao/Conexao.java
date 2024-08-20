package ifsc.ctds.tarefa.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao {
	private static final String LOGIN_BANCO = "root";
	private static final String SENHA_BANCO = "";
	private static final String URL_BANCO = 
	  "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
	
	public static Connection getConnection() {
		Connection conexao = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //importa o drive
			conexao = (Connection) DriverManager.getConnection(
					URL_BANCO, LOGIN_BANCO,	SENHA_BANCO);
		} catch(SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados. Error: " + e);
		} catch(ClassNotFoundException e) {
			System.out.println("Não foi possível carregar a classe JDBC Mysql. Error: " + e);
		} catch (Exception e) {
			System.out.println("Erro geral. Error: " + e);
		}
		//retorna a conexão em caso de sucesso
		return conexao;		
	}
	
	public static void main(String[] args) {
		Connection conexao = getConnection();
				
		if (conexao != null) {
			System.out.println("Conexão obtidada com sucesso");
		}
	} 
}

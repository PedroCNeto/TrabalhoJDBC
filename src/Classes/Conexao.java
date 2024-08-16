package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
	
	private Connection Con;
	
	public boolean ConectaBD(String Usuario,
			  		  String Senha,
			  		  String Servidor,
			  		  String Porta,
			  		  String BancoDados){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String URL = "jdbc:mysql://" + 
						 Servidor + ":" + Porta + "/" + 
						 BancoDados;
			this.Con = DriverManager.getConnection(
					URL, Usuario, Senha);
			System.out.println(URL);

			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	Connection getConexao(){
		return this.Con;
	}

	public boolean ExecutaSQL(String Fonte ,String ComandoSQL,
					   ArrayList<String> Params) {
		try {
			String nextId = "";
			if(Fonte.equals("Empresa")) {
				String getNextIdQuery = "SELECT COUNT(idEmpresa) + 1 FROM Empresa";
				PreparedStatement stmt = Con.prepareStatement(getNextIdQuery);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
			    	nextId = rs.getString(1);
				}
			}
			else {
				String getNextIdQuery = "SELECT COUNT(idFuncionario) + 1 FROM Funcionario";
				PreparedStatement stmt = Con.prepareStatement(getNextIdQuery);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
			    	nextId = rs.getString(1);
				}
			}
			
			PreparedStatement pst = 
					this.Con.prepareStatement(ComandoSQL);
			pst.clearParameters();
			pst.setString(1, nextId);
			for(int cont=1; cont <= Params.size(); cont++) {
				pst.setString(cont+1, Params.get(cont-1));
			}
			return pst.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	int buscarIdEmp() {
		Statement st;
		try {
			st = this.Con.createStatement();
			ResultSet resultado = st.executeQuery("Select Count(id) from empresa");
			int val = ((Number) resultado.getObject(1)).intValue();
			return val + 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public List<Empresa> ExecutaConsultaEmpresa(){
		try {
			Statement st = this.Con.createStatement();
			ResultSet resultado = st.executeQuery("Select * from Empresa");
			List<Empresa> Lista = new ArrayList<Empresa>();
			while(resultado.next()) {
				Empresa E1 = new Empresa();
				E1.setId(resultado.getInt("idEmpresa"));
				E1.setNome(resultado.getString("Nome"));
				E1.setCNPJ(resultado.getString("CNPJ"));
				E1.setRamo(resultado.getString("RamoAtuacao"));
				Lista.add(E1);
			}
			return Lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Empresa>();	
	}

	public List<Funcionario> ExecutaConsultaFunc() {
		try {
			Statement st = this.Con.createStatement();
			ResultSet resultado = st.executeQuery("Select * from Funcionario");
			List<Funcionario> Lista = new ArrayList<Funcionario>();
			while(resultado.next()) {
				Funcionario F1 = new Funcionario();
				F1.setId(resultado.getInt("idFuncionario"));
				F1.setNome(resultado.getString("Nome"));
				F1.setCPF(resultado.getString("CPF"));
				Lista.add(F1);
			}
			return Lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Funcionario>();	
	}
	
	/*List<Aluno> ExecutaConsultaFunc(String ComandoSQL){
		try {
			Statement st = this.Con.createStatement();
			ResultSet resultado = st.executeQuery(ComandoSQL);
			List<Aluno> Lista = new ArrayList<Aluno>();
			while(resultado.next()) {
				Aluno A1 = new Aluno();
				A1.setNome(resultado.getString("Nome"));
				A1.setMatricula(resultado.getString("Matricula"));
				A1.setCurso(resultado.getInt("Curso"));
				A1.setTurno(resultado.getInt("Turno"));
				Lista.add(A1);
			}
			return Lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Aluno>();	
	}*/
	
}

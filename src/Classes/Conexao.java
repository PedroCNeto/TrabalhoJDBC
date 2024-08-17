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
			else if (Fonte.equals("Funcionario")) {
				String getNextIdQuery = "SELECT COUNT(idFuncionario) + 1 FROM Funcionario";
				PreparedStatement stmt = Con.prepareStatement(getNextIdQuery);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
			    	nextId = rs.getString(1);
				}
			}
			else if (Fonte.equals("Associacao")) {
				String getNextIdQuery = "SELECT COUNT(idFuncionarioEmpresa) + 1 FROM FuncionarioEmpresa";
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
	
	public ArrayList<Associacao> ExecutaConsultaAssoc() {
		try {
			Statement st = this.Con.createStatement();
			ResultSet resultado = st.executeQuery("Select * from FuncionarioEmpresa");
			ArrayList<Associacao> Lista = new ArrayList<Associacao>();
			while(resultado.next()) {
				Associacao FE1 = new Associacao();
				FE1.setId(resultado.getInt("idFuncionarioEmpresa"));
				FE1.setIdFunc(resultado.getInt("idFuncionario"));
				FE1.setIdEmp(resultado.getInt("idEmpresa"));
				FE1.setDtAdim(resultado.getString("DataAdmissao"));
				FE1.setDtDemi(resultado.getString("DataDemissao"));
				Lista.add(FE1);
			}
			return Lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Associacao>();	
	}
	
	public String buscarNome(String tipo, int id) {
	    String comando;
	    
	    if(tipo.equals("Func")) {
	        comando = "SELECT Nome FROM Funcionario Where idFuncionario = ?";
	    } else {
	        comando = "SELECT Nome FROM Empresa Where idEmpresa = ?";
	    }
	    
	    try {
	        PreparedStatement pst = Con.prepareStatement(comando);
	        pst.clearParameters();
	        pst.setInt(1, id);
	        ResultSet nome = pst.executeQuery();
	        
	        if (nome.next()) {
	            return nome.getString(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return "";
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

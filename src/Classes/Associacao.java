package Classes;

import Janelas.Principal;

public class Associacao {
	private int id;
	private int idFunc;
	private int idEmp;
	private String dtAdim;
	private String dtDemi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	public int getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}
	public String getDtAdim() {
		return dtAdim;
	}
	public void setDtAdim(String dtAdim) {
		this.dtAdim = dtAdim;
	}
	public String getDtDemi() {
		return dtDemi;
	}
	public void setDtDemi(String dtDemi) {
		this.dtDemi = dtDemi;
	}
	
	public String buscaNome(String tipo, int id) {
		return Principal.buscaNome(tipo, id);
	}
	
	
}

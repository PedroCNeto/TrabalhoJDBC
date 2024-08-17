package Janelas;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import Classes.Associacao;
import Classes.Empresa;
import Classes.Funcionario;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

import Classes.ComboItem;


public class MenuAssociacao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtDt;

	/**
	 * Create the panel.
	 */
	private JTextField txtDataDemi;

	static JComboBox<ComboItem> cb_Func = new JComboBox<ComboItem>();
	static JComboBox<ComboItem> cb_Emp = new JComboBox<ComboItem>();

	static public void carregarCBFunc() {
	    cb_Func.removeAllItems(); 
	    ArrayList<Funcionario> funcs = Principal.getFuncionarios();
	    for (Funcionario funcionario : funcs) {
	        cb_Func.addItem(new ComboItem(funcionario.getId(), funcionario.getNome()));
	    }
	}

	static public void carregarCBEmp() {
		cb_Emp.removeAllItems(); 
	    ArrayList<Empresa> emps = Principal.getEmpresas();
	    for (Empresa empresa : emps) {
	        cb_Emp.addItem(new ComboItem(empresa.getId(), empresa.getNome()));
	    }
	}
	
	public void atualizarModel(JTable table) {
        ArrayList<Associacao> assocs2 = Principal.getAssociacao();
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Func");
        model.addColumn("Emp");
        model.addColumn("Data Adm");

        for (Associacao assoc : assocs2) {
            model.addRow(new Object[]{
            		assoc.buscaNome("Func", assoc.getIdFunc()),
            		assoc.buscaNome("Emp", assoc.getIdEmp()),
            		assoc.getDtAdim()
            });
        }
		table.setModel(model);
	}
	
	public MenuAssociacao() {
		setFont(new Font("Tahoma", Font.BOLD, 15));
		setLayout(null);
		
		JLabel lblNome = new JLabel("*Funcionario:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNome.setBounds(47, 76, 103, 14);
		add(lblNome);
		
		JLabel lblEmp = new JLabel("*Empresa:");
		lblEmp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmp.setBounds(47, 109, 103, 14);
		add(lblEmp);
		
		JLabel lblTitulo = new JLabel("Tele de Associação");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 27, 594, 36);
		add(lblTitulo);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtDt.getText().isEmpty()) {
					System.out.println("Preencha os campos necessarios");
				}
				else {
					ComboItem selectedFunc = (ComboItem) cb_Func.getSelectedItem();
					 int selectedFuncId = selectedFunc.getId();

					ComboItem selectedEmp = (ComboItem) cb_Emp.getSelectedItem();
					 int selectedEmpId = selectedEmp.getId();
					
					Principal.insertAssoc(String.valueOf(selectedFuncId), String.valueOf(selectedEmpId), txtDt.getText(), txtDataDemi.getText());
					atualizarModel(table);		
					System.out.println("Associacao feita com sucesso!");
					
					txtDataDemi.setText("");
					txtDt.setText("");
				}
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCadastrar.setBounds(121, 238, 126, 51);
		add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(298, 97, 286, 178);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		atualizarModel(table);
		
		
		
		cb_Func.setBounds(160, 74, 115, 22);
		add(cb_Func);
		carregarCBFunc();
		
		cb_Emp.setBounds(160, 107, 115, 22);
		add(cb_Emp);
		carregarCBEmp();
		
		JLabel lblAdm = new JLabel("*Data de Admissão:");
		lblAdm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdm.setBounds(0, 145, 150, 14);
		add(lblAdm);
		
		txtDt = new JTextField();
		txtDt.setBounds(158, 140, 130, 28);
		add(txtDt);
		txtDt.setColumns(10);
		
		txtDataDemi = new JTextField();
		txtDataDemi.setColumns(10);
		txtDataDemi.setBounds(160, 179, 128, 28);
		add(txtDataDemi);
		
		JLabel lblDataDemissaonull = new JLabel("Data Demissao");
		lblDataDemissaonull.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDemissaonull.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDataDemissaonull.setBounds(0, 184, 150, 14);
		add(lblDataDemissaonull);
	}
}

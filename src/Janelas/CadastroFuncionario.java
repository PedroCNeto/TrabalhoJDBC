package Janelas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Classes.Empresa;
import Classes.Funcionario;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadastroFuncionario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTable table;

	/**
	 * Create the panel.
	 */
	
	public void atualizarModel(JTable table) {
        ArrayList<Funcionario> funcs = Principal.getFuncionarios();
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Func");
        model.addColumn("Nome");
        
        for (Funcionario func : funcs) {
            model.addRow(new Object[]{
            		func.getId(),
            		func.getNome(),
            });
        }
		table.setModel(model);
	}
	
	public CadastroFuncionario() {
		setFont(new Font("Tahoma", Font.BOLD, 15));
		setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(177, 97, 176, 36);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(177, 179, 176, 36);
		add(txtCPF);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNome.setBounds(79, 106, 88, 14);
		add(lblNome);
		
		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(79, 190, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblTitulo = new JLabel("Cadastrar Funcionarios");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 27, 594, 36);
		add(lblTitulo);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCPF.getText().isEmpty() || txtNome.getText().isEmpty()) {
					System.out.println("Preencha todos os campos!");
				}
				else {
					Principal.insertFunc(txtNome.getText(), txtCPF.getText());
					atualizarModel(table);
		            MenuAssociacao.carregarCBFunc();		
		            txtCPF.setText("");
		            txtNome.setText("");
					System.out.println("Funcionario registrado com sucesso!");
				}
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCadastrar.setBounds(177, 237, 126, 51);
		add(btnCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(369, 97, 215, 178);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		atualizarModel(table);
	}
}

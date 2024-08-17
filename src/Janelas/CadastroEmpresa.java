package Janelas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Classes.Empresa;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CadastroEmpresa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCNPJ;
	private JTextField txtRamo;
	private JTable table;

	/**
	 * Create the panel.
	 */
	

	public void atualizarModel(JTable table) {
        ArrayList<Empresa> empresas = Principal.getEmpresas();
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID Empresa");
        model.addColumn("Nome");
        
        for (Empresa empresa : empresas) {
            model.addRow(new Object[]{
                empresa.getId(),
                empresa.getNome(),
            });
        }
		table.setModel(model);
	}
	
	public CadastroEmpresa() {
		
		setFont(new Font("Tahoma", Font.BOLD, 15));
		setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(177, 97, 176, 36);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtCNPJ = new JTextField();
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(177, 144, 176, 36);
		add(txtCNPJ);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNome.setBounds(95, 106, 53, 14);
		add(lblNome);
		
		JLabel lblNewLabel_1 = new JLabel("CNPJ:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(102, 153, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblTitulo = new JLabel("Cadastrar Empresas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 27, 594, 36);
		add(lblTitulo);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCNPJ.getText().isEmpty() || txtNome.getText().isEmpty() || txtRamo.getText().isEmpty()) {
					System.out.println("Preencha todos os campos!");
				}
				else {
					Principal.insertEmp(txtNome.getText(), txtCNPJ.getText(), txtRamo.getText());
			        atualizarModel(table);
			        MenuAssociacao.carregarCBEmp();
			        System.out.println("Empresa registrada com sucesso!");
			        txtNome.setText("");
			        txtCNPJ.setText("");
			        txtRamo.setText("");
				}

			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCadastrar.setBounds(177, 237, 126, 51);
		add(btnCadastrar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ramo de Atuação:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 200, 138, 14);
		add(lblNewLabel_1_1);
		
		txtRamo = new JTextField();
		txtRamo.setColumns(10);
		txtRamo.setBounds(177, 191, 176, 36);
		add(txtRamo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(363, 97, 215, 187);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

        
        atualizarModel(table);
	}
}

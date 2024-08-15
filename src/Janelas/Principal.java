package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void addMenuFunc(JPanel ContentPane, CadastroFuncionario item) {
		item.setVisible(false);
		item.setBounds(0, 50, 595, 441);
		ContentPane.add(item);
	}
	
	public void esconderMenu(JPanel menuNovo,JPanel menuVelho) {
		menuVelho.setVisible(false);
		menuNovo.setVisible(true);
	}
	
	public Principal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Principal = new JPanel();
		Principal.setBounds(0, 45, 595, 385);
		contentPane.add(Principal);
		Principal.setLayout(null);
		
		CadastroFuncionario menuFunc = new CadastroFuncionario();
		addMenuFunc(contentPane, menuFunc);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setVisible(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderMenu(Principal, menuFunc);
				btnVoltar.setVisible(false);
			}
		});
		
		JLabel lblTitulo = new JLabel("Gerenciamento de Empresas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTitulo.setBounds(0, 37, 595, 73);
		Principal.add(lblTitulo);
		
		JButton btnNewButton = new JButton("Gerir Empresas");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(342, 165, 169, 73);
		Principal.add(btnNewButton);
		
		JButton btnFunc = new JButton("Gerir Funcionarios");
		btnFunc.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderMenu(menuFunc, Principal);
				btnVoltar.setVisible(true);
			}
		});
		btnFunc.setBounds(92, 165, 183, 73);
		Principal.add(btnFunc);
		
		JButton btnAtribuir = new JButton("Atribuir funcionarios e empresas");
		btnAtribuir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtribuir.setBounds(92, 273, 419, 53);
		Principal.add(btnAtribuir);
		
		JLabel lblNewLabel = new JLabel("Copyright Pêdro C. Neto 2024");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 351, 595, 21);
		Principal.add(lblNewLabel);
		

		btnVoltar.setBounds(36, 11, 89, 23);
		contentPane.add(btnVoltar);
	}
}

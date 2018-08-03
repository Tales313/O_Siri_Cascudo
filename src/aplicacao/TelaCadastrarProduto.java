package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

public class TelaCadastrarProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNome;
	private JLabel lblPreco;
	private JButton btnCriar;

	/**
	 * Create the frame.
	 */
	public TelaCadastrarProduto() {
		setTitle("Cadastrar Produto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 244, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(85, 25, 121, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(23, 28, 46, 14);
		contentPane.add(lblNome);

		lblPreco = new JLabel("Preco");
		lblPreco.setBounds(23, 66, 46, 14);
		contentPane.add(lblPreco);

		textField_1 = new JTextField();
		textField_1.setBounds(85, 63, 121, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nome = textField.getText();
					double preco = Double.parseDouble(textField_1.getText());
					Fachada.cadastrarProduto(nome, preco);
					JOptionPane.showMessageDialog(rootPane, nome+" cadastrado com sucesso!", "Sucesso", 1);			
					textField.setText("");
					textField_1.setText("");
					textField.requestFocus();
				}
				catch(Exception err){
					JOptionPane.showMessageDialog(rootPane, err.getMessage(), "Erro", 2);
				}
			}
		});
		btnCriar.setBounds(59, 116, 115, 23);
		contentPane.add(btnCriar);
	}
}

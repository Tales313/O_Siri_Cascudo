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

public class TelaCadastrarGarcom extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblApelido;
	private JLabel lblIdMesaInicial;
	private JButton btnCriar;
	private JLabel lblIdMesaFinal;
	private JTextField textField_3;

	/**
	 * Create the frame.
	 */
	
	public TelaCadastrarGarcom() {
		setTitle("Cadastrar Garçom");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 271, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblApelido = new JLabel("Apelido");
		lblApelido.setBounds(37, 14, 98, 14);
		contentPane.add(lblApelido);

		textField_1 = new JTextField();
		textField_1.setBounds(145, 11, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		lblIdMesaInicial = new JLabel("Id Mesa Inicial");
		lblIdMesaInicial.setBounds(37, 56, 98, 14);
		contentPane.add(lblIdMesaInicial);

		textField_2 = new JTextField();
		textField_2.setBounds(145, 53, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblIdMesaFinal = new JLabel("Id Mesa Final");
		lblIdMesaFinal.setBounds(37, 102, 98, 14);
		contentPane.add(lblIdMesaFinal);
		
		textField_3 = new JTextField();
		textField_3.setBounds(145, 99, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String apelido = textField_1.getText();
					int mesaInicial = Integer.parseInt(textField_2.getText());
					int mesaFinal = Integer.parseInt(textField_3.getText());
					Fachada.cadastrarGarcom(apelido, mesaInicial, mesaFinal);
					JOptionPane.showMessageDialog(rootPane, "Garçom Cadastrado!", "Sucesso", 1);
				}catch(Exception err) {
					JOptionPane.showMessageDialog(rootPane, err.getMessage(), "Erro", 2);
				}
				textField_1.requestFocus();
			}
		});
		btnCriar.setBounds(75, 154, 115, 23);
		contentPane.add(btnCriar);
	}
}

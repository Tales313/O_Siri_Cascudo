package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Conta;
import modelo.Mesa;

public class TelaConsultarConta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMesa;
	private JComboBox<String> comboMesa;

	/**
	 * Create the frame.
	 */
	
	public TelaConsultarConta() {
		setTitle("Consultar Conta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 227, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMesa = new JLabel("Id da mesa:");
		lblMesa.setBounds(10, 31, 100, 14);
		contentPane.add(lblMesa);
		
		JButton btnCriar = new JButton("Consultar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int idMesa = Integer.parseInt(comboMesa.getSelectedItem().toString());
					Conta c = Fachada.consultarConta(idMesa);
					JOptionPane.showMessageDialog(rootPane, c, "Sucesso", 1);
				}catch(Exception err) {
					JOptionPane.showMessageDialog(rootPane, err.getMessage(), "Erro", 2);
				}
			}
		});
		btnCriar.setBounds(62, 89, 89, 23);
		contentPane.add(btnCriar);
		
		comboMesa = new JComboBox<>();
		for(Mesa m: Fachada.getR().getMesas()) 
			comboMesa.addItem(Integer.toString(m.getId()));
		comboMesa.setBounds(99, 28, 100, 20);
		comboMesa.setSelectedIndex(0);
		contentPane.add(comboMesa);
	}
}

package aplicacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mesa;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaFecharConta extends JFrame {

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
	
	public TelaFecharConta() {
		setTitle("Fechar Conta");
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
		
		JButton btnCriar = new JButton("Fechar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int idMesa = Integer.parseInt(comboMesa.getSelectedItem().toString());
					Fachada.fecharConta(idMesa);
					JOptionPane.showMessageDialog(rootPane, "Conta fechada na mesa "+idMesa, "Sucesso", 1);
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

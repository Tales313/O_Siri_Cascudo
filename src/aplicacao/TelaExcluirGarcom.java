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
import modelo.Garcom;

public class TelaExcluirGarcom extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblApelido;
	private JComboBox<String> comboGarcom;

	/**
	 * Create the frame.
	 */
	
	public TelaExcluirGarcom() {
		setTitle("Excluir Garçom");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 238, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblApelido = new JLabel("Apelido: ");
		lblApelido.setBounds(31, 31, 58, 14);
		contentPane.add(lblApelido);
		
		JButton btnCalcular = new JButton("Excluir");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String apelido = comboGarcom.getSelectedItem().toString();
				try {
					Fachada.excluirGarcom(apelido);
					JOptionPane.showMessageDialog(rootPane, "Garçom '" + apelido + "' excluido!", "Sucesso", 1);
				}catch(Exception err) {
					JOptionPane.showMessageDialog(rootPane, err.getMessage(), "Erro", 2);
				}
			}
		});
		btnCalcular.setBounds(70, 87, 89, 23);
		contentPane.add(btnCalcular);
		
		comboGarcom = new JComboBox<String>();	
		for(Garcom g: Fachada.getR().getGarcons()) 
			comboGarcom.addItem(g.getApelido());
		comboGarcom.setSelectedIndex(0);
		comboGarcom.setBounds(99, 28, 103, 20);
		contentPane.add(comboGarcom);
	}
}

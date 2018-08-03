package aplicacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Garcom;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaPercentualMedio extends JFrame {

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
	
	public TelaPercentualMedio() {
		setTitle("Calcular Percentual Médio");
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
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String apelido = comboGarcom.getSelectedItem().toString();
				try {
					double g = Fachada.calcularPercentualMedio(apelido);
					JOptionPane.showMessageDialog(rootPane, "Percentual Médio do " + apelido + " = " + g, "Sucesso", 1);
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

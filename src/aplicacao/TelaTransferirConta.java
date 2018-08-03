package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mesa;

import javax.swing.JComboBox;

public class TelaTransferirConta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblOrigem;
	private JLabel lblDestino;
	JComboBox<String> comboOrigem;
	JComboBox<String> comboDestino;
	private JButton btnTransferir;

	/**
	 * Create the frame.
	 */
	public TelaTransferirConta() {
		setTitle("Transferir Conta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 269, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblOrigem = new JLabel("Id mesa origem");
		lblOrigem.setBounds(25, 25, 101, 14);
		contentPane.add(lblOrigem);

		lblDestino = new JLabel("Id mesa destino");
		lblDestino.setBounds(25, 63, 101, 14);
		contentPane.add(lblDestino);
		
		comboOrigem = new JComboBox<>();
		for(Mesa m: Fachada.getR().getMesas()) 
			comboOrigem.addItem(Integer.toString(m.getId()));
		comboOrigem.setSelectedIndex(0);
		comboOrigem.setBounds(147, 22, 87, 20);
		contentPane.add(comboOrigem);
		
		comboDestino = new JComboBox<>();
		for(Mesa m: Fachada.getR().getMesas()) 
			comboDestino.addItem(Integer.toString(m.getId()));
		comboDestino.setSelectedIndex(0);
		comboDestino.setBounds(147, 60, 87, 20);
		contentPane.add(comboDestino);
		
				btnTransferir = new JButton("Transferir");
				btnTransferir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							int idMesaOrigem = Integer.parseInt(comboOrigem.getSelectedItem().toString());
							int idMesaDestino = Integer.parseInt(comboDestino.getSelectedItem().toString());
							Fachada.transferirConta(idMesaOrigem, idMesaDestino);
							JOptionPane.showMessageDialog(rootPane, "Conta transferida com sucesso!", "Sucesso", 1);
						}
						catch(Exception err){
							JOptionPane.showMessageDialog(rootPane, err.getMessage(), "Erro", 2);
						}
					}
				});
				btnTransferir.setBounds(67, 110, 115, 23);
				contentPane.add(btnTransferir);
	}
}

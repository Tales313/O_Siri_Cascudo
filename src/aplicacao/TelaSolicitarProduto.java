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
import modelo.Produto;

import javax.swing.JComboBox;

public class TelaSolicitarProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMesa;
	private JLabel lblProduto;
	private JButton btnSolicitar;
	private JComboBox<String> comboMesa;
	private JComboBox<String> comboProduto;

	/**
	 * Create the frame.
	 */
	public TelaSolicitarProduto() {
		setTitle("Solicitar Produto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 231, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMesa = new JLabel("Id da Mesa:");
		lblMesa.setBounds(21, 14, 78, 14);
		contentPane.add(lblMesa);

		lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(21, 66, 67, 14);
		contentPane.add(lblProduto);
		
		comboMesa = new JComboBox<>();
		comboMesa.setBounds(109, 11, 95, 20);
		for(Mesa m: Fachada.getR().getMesas()) 
			comboMesa.addItem(Integer.toString(m.getId()));
		comboMesa.setSelectedIndex(0);
		contentPane.add(comboMesa);
		
		comboProduto = new JComboBox<>();
		comboProduto.setBounds(109, 63, 95, 20);
		for(Produto p: Fachada.getR().getProdutos()) 
			comboProduto.addItem(p.getNome());
		comboProduto.setSelectedIndex(0);
		contentPane.add(comboProduto);
		
		btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int idMesa = Integer.parseInt(comboMesa.getSelectedItem().toString());
					String nome = comboProduto.getSelectedItem().toString();
					Fachada.solicitarProduto(idMesa, nome);
					JOptionPane.showMessageDialog(rootPane, nome+" solicitado na mesa "+idMesa, "Sucesso", 1);
				}
				catch(Exception err){
					JOptionPane.showMessageDialog(rootPane, err.getMessage(), "Erro", 2);
				}
			}
		});
		btnSolicitar.setBounds(57, 119, 115, 23);
		contentPane.add(btnSolicitar);
	}
}

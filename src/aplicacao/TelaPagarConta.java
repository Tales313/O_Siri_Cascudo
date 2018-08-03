package aplicacao;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import fachada.Fachada;
import modelo.Mesa;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TelaPagarConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblPagamento;
	private JComboBox<String> comboPagamento;
	private JLabel lblDesconto;
	private JComboBox<String> comboPct;
	private JLabel lblCartao;
	private JComboBox<String> comboCartao;
	private JLabel lblParcelas;
	private JComboBox<String> comboParcelas;
	private JComboBox<String> comboMesa;

	/**
	 * Initialize the contents of the frame.
	 */
	public TelaPagarConta() {
		setTitle("Pagamento");
		setBounds(100, 100, 331, 334);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIdMesa = new JLabel("Id da mesa:");
		lblIdMesa.setBounds(67, 14, 66, 14);
		getContentPane().add(lblIdMesa);
		
		comboMesa = new JComboBox<>();
		comboMesa.setBounds(154, 11, 86, 20);
		getContentPane().add(comboMesa);
		for(Mesa m: Fachada.getR().getMesas()) 
			comboMesa.addItem(Integer.toString(m.getId()));
		comboMesa.setSelectedIndex(0);
		
		lblPagamento = new JLabel("Pagamento:");
		lblPagamento.setBounds(67, 56, 72, 14);
		getContentPane().add(lblPagamento);
		comboPagamento = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"Dinheiro", "Cart\u00E3o"}));
		comboPagamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if(arg0.getSource().toString().contains("Dinheiro")) {
					comboPct.setEnabled(true);
					comboCartao.setEnabled(false);
					comboParcelas.setEnabled(false);
				}else {
					comboPct.setEnabled(false);
					comboCartao.setEnabled(true);
					comboParcelas.setEnabled(true);
				}
				
			}
		});
		comboPagamento.setBounds(154, 53, 86, 20);
		getContentPane().add(comboPagamento);
		
		lblDesconto = new JLabel("Desconto:");
		lblDesconto.setBounds(67, 107, 66, 14);
		getContentPane().add(lblDesconto);
		
		comboPct = new JComboBox<String>();
		comboPct.setModel(new DefaultComboBoxModel<String>(new String[] {"0%", "1%", "2%", "3%", "4%", "5%"}));
		comboPct.setSelectedIndex(0);
		comboPct.setBounds(154, 104, 86, 20);
		getContentPane().add(comboPct);
		
		lblCartao = new JLabel("Cart\u00E3o:");
		lblCartao.setBounds(67, 158, 46, 14);
		getContentPane().add(lblCartao);
		
		comboCartao = new JComboBox<String>();
		comboCartao.setModel(new DefaultComboBoxModel<String>(new String[] {"Visa", "MasterCard", "Elo"}));
		comboCartao.setSelectedIndex(0);
		comboCartao.setEnabled(false);
		comboCartao.setBounds(154, 155, 86, 20);
		getContentPane().add(comboCartao);
		
		lblParcelas = new JLabel("Parcelas:");
		lblParcelas.setBounds(67, 209, 66, 14);
		getContentPane().add(lblParcelas);
		
		comboParcelas = new JComboBox<String>();
		comboParcelas.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4"}));
		comboParcelas.setSelectedIndex(0);
		comboParcelas.setEnabled(false);
		comboParcelas.setBounds(154, 206, 86, 20);
		getContentPane().add(comboParcelas);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				try {			
					int idMesa = Integer.parseInt(comboMesa.getSelectedItem().toString());
					String tipo = comboPagamento.getSelectedItem().toString();
					
					if(tipo.equals("Dinheiro")) {						
						int percentual = Integer.parseInt(comboPct.getSelectedItem().toString().substring(0, 1));
						Fachada.pagarConta(idMesa, "dinheiro", percentual, "", 0);						
					}else if(tipo.equals("Cartão")) {
						String cartao = comboCartao.getSelectedItem().toString();
						int qtdParcelas = Integer.parseInt(comboParcelas.getSelectedItem().toString());
						Fachada.pagarConta(idMesa, "cartao", 0, cartao, qtdParcelas);			
					}				
					JOptionPane.showMessageDialog(rootPane, "Conta paga com sucesso!", "Sucesso", 1);					
				}catch(Exception err) {
					JOptionPane.showMessageDialog(rootPane, err.getMessage(), "Erro", 2);
				}			
			}
		});
		btnPagar.setBounds(110, 254, 89, 23);
		getContentPane().add(btnPagar);
	}
}

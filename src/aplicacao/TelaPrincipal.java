package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fachada.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(Fachada.getNomeRestaurante());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		try {
			Fachada.criarMesas(20);
			Fachada.cadastrarProduto("arroz", 3.0);
			Fachada.cadastrarProduto("feijao", 5.0);
			Fachada.cadastrarProduto("macarrao", 4.0);
			Fachada.cadastrarProduto("carne", 30.0);
			Fachada.cadastrarProduto("frango", 22.5);
			Fachada.cadastrarProduto("batata", 11.0);
			Fachada.cadastrarProduto("refrigerante", 8.5);
			Fachada.cadastrarProduto("cerveja", 10.0);
			Fachada.cadastrarGarcom("consagrado", 1, 5);
			Fachada.cadastrarGarcom("mestre", 6, 10);
			Fachada.cadastrarGarcom("fidedigno", 11, 15);
			Fachada.cadastrarGarcom("magnata", 16, 20);
		} catch(Exception e) {e.printStackTrace();}
		
		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imgs/oSiriCascudo.png")));
		lblFundo.setBounds(0, 21, 444, 251);
		frame.getContentPane().add(lblFundo);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 444, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnContas = new JMenu("Contas");
		menuBar.add(mnContas);
		
		JMenuItem mntmCriar = new JMenuItem("Criar");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCriarConta j = new TelaCriarConta();
				j.setVisible(true);
			}
		});
		mnContas.add(mntmCriar);
		
		JMenuItem mntmFechar = new JMenuItem("Fechar");
		mntmFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFecharConta j = new TelaFecharConta();
				j.setVisible(true);
			}
		});
		mnContas.add(mntmFechar);
		
		JMenuItem mntmCancelar = new JMenuItem("Cancelar");
		mntmCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCancelarConta j = new TelaCancelarConta();
				j.setVisible(true);
			}
		});
		mnContas.add(mntmCancelar);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultarConta j = new TelaConsultarConta();
				j.setVisible(true);
			}
		});
		mnContas.add(mntmConsultar);
		
		JMenuItem mntmTransferir = new JMenuItem("Transferir");
		mntmTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaTransferirConta j = new TelaTransferirConta();
				j.setVisible(true);
			}
		});
		mnContas.add(mntmTransferir);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarContas j = new TelaListarContas();
				j.setVisible(true);
			}
		});
		mnContas.add(mntmListar);
		
		JMenuItem mntmPagar = new JMenuItem("Pagar");
		mntmPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPagarConta j = new TelaPagarConta();
				j.setVisible(true);
			}
		});
		mnContas.add(mntmPagar);
		
		JMenu mnProdutos = new JMenu("Produtos");
		menuBar.add(mnProdutos);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarProduto j = new TelaCadastrarProduto();
				j.setVisible(true);
			}
		});
		mnProdutos.add(mntmCadastrar);
		
		JMenuItem mntmSolicitar = new JMenuItem("Solicitar");
		mntmSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSolicitarProduto j = new TelaSolicitarProduto();
				j.setVisible(true);
			}
		});
		mnProdutos.add(mntmSolicitar);
		
		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListarProdutos j = new TelaListarProdutos();
				j.setVisible(true);
			}
		});
		mnProdutos.add(mntmListar_1);
		
		JMenu mnGarcons = new JMenu("Gar\u00E7ons");
		menuBar.add(mnGarcons);
		
		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mntmCadastrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarGarcom j = new TelaCadastrarGarcom();
				j.setVisible(true);
			}
		});
		mnGarcons.add(mntmCadastrar_1);
		
		JMenuItem mntmCalcularGorjeta = new JMenuItem("Calcular Gorjeta");
		mntmCalcularGorjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCalcularGorjeta j = new TelaCalcularGorjeta();
				j.setVisible(true);
			}
		});
		mnGarcons.add(mntmCalcularGorjeta);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mntmListar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarGarcons j = new TelaListarGarcons();
				j.setVisible(true);
			}
		});
		mnGarcons.add(mntmListar_2);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaExcluirGarcom j = new TelaExcluirGarcom();
				j.setVisible(true);
			}
		});
		mnGarcons.add(mntmExcluir);
		
		JMenuItem mntmPercentualMedio = new JMenuItem("Percentual Medio");
		mntmPercentualMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPercentualMedio j = new TelaPercentualMedio();
				j.setVisible(true);
			}
		});
		mnGarcons.add(mntmPercentualMedio);
		
		JMenu mnMesas = new JMenu("Mesas");
		menuBar.add(mnMesas);
		
		JMenuItem mntmListar_3 = new JMenuItem("Listar");
		mntmListar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarMesas j = new TelaListarMesas();
				j.setVisible(true);
			}
		});
		mnMesas.add(mntmListar_3);
	}
}

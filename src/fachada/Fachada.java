package fachada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Pagamento;
import modelo.PagamentoCartao;
import modelo.PagamentoDinheiro;
import modelo.Produto;
import repositorio.Restaurante;

public class Fachada {
	
	private static Restaurante r = new Restaurante("O Siri Cascudo");
	
	public static String getNomeRestaurante() {
		return r.getNome();
	}
	
	public static Restaurante getR() {
		return r;
	}

	public static Produto cadastrarProduto(String nome, double preco) throws Exception {
		
		if(preco < 0)
			throw new Exception("Preço inválido");
		
		Produto p = r.localizarProduto(nome);
		if(p != null)
			throw new Exception("O produto " + nome + " já foi cadastrado");
		
		p = new Produto(nome, preco);
		r.addProduto(p);
		return p;
		
	}
	
	public static ArrayList<Produto> listarProdutos(String nome) {
		/* O parâmetro nome pode estar cortado. Exemplo: pe (peixe, peito de frango) */
		
		ArrayList<Produto> produtos = r.localizarProdutos(nome);
		Collections.sort(produtos);
		return produtos;
		
	}
	
	public static void criarMesas(int n) throws Exception {
		if(n <= 0)
			throw new Exception("Número de mesas inválido");
			
		for(int i=0; i<n; i++)
			r.addMesa(new Mesa());
	}
	
	public static ArrayList<Mesa> listarMesas() {
		return r.getMesas();
	}
	
	public static Garcom cadastrarGarcom(String apelido, int mesaInicial, int mesaFinal) throws Exception {
		/* Primeiro esse método vai verificar se o garçom informado já existe, depois vai verificar se
		 * todas as mesas dentro do intervalo também existem e se elas ainda não possuem garçom, pra 
		 * só depois cadastrar o garçom de fato. */
		Garcom g;
		Mesa m;
		
		apelido = apelido.toLowerCase();
		
		g = r.localizarGarcom(apelido); //verificando se o garçom já existe
		if(g != null)
			throw new Exception("O Garçom " + apelido + " já foi cadastrado");
		
		if(mesaInicial <= 0)
			throw new Exception("Mesa inicial inválida");
		
		if(mesaFinal <= 0)
			throw new Exception("Mesa final inválida");
		
		if(mesaInicial > mesaFinal)
			throw new Exception("A mesa inicial deve ser menor que a final.");
		
		if(mesaFinal - mesaInicial != 4)
			throw new Exception("Cada garçom deve ter 5 mesas");
		
		for(int i=mesaInicial; i<=mesaFinal; i++) { //verificando se as mesas existem
			m = r.localizarMesa(i);
			if(m == null)
				throw new Exception("A mesa " + i +  " não existe no sistema");
			else if(m.getGarcom() != null)
				throw new Exception("A mesa " + i + " já tem garçom");
		}
		
		g = new Garcom(apelido);
		
		for(int i=mesaInicial; i<=mesaFinal; i++) {
			m = r.localizarMesa(i);
			m.setGarcom(g);
			g.addMesa(m);
		}
		
		r.addGarcom(g);
		return g;
	}
	
	public static ArrayList<Garcom> listarGarcons() {
		return r.getGarcons();
	}
	
	public static void criarConta(int idMesa) throws Exception {
		/* Esse método primeiro vai testar se a mesa em questão existe, depois vai testar se a mesa
		 * ainda está ocupada por outros clientes. */
		
		Mesa m = r.localizarMesa(idMesa);
		if(m == null)
			throw new Exception("A mesa " + idMesa + " não existe no sistema");
		
		if(m.getGarcom() == null)
			throw new Exception("A mesa " + idMesa + " está sem garçom");
		
		Conta c = m.getUltimaConta();
		if(c != null)
			if(c.getPagamento() == null)
				throw new Exception("A mesa " + idMesa + " ainda tem uma conta não paga");
		
		c = new Conta(m);
		m.addConta(c);
		r.addConta(c);
		m.setOcupada(true);
	}
	
	public static void solicitarProduto(int idMesa, String nome) throws Exception {
		/* Primeiro testa se a mesa em questão existe, depois testa se o produto pedido também existe,
		 * e por fim testa se a mesa realmente tem clientes. */
		
		Mesa m = r.localizarMesa(idMesa);
		if(m == null)
			throw new Exception("A mesa " + idMesa + " não existe no sistema");
		
		if(m.getGarcom() == null)
			throw new Exception("A mesa " + idMesa + " está sem garçom");
		
		Produto p = r.localizarProduto(nome);
		if(p == null)
			throw new Exception("O produto " + nome + " não existe no sistema");
		
		Conta c = m.getUltimaConta();
		if(c == null)
			throw new Exception("Não existe nenhuma conta na mesa " + idMesa);
		
		if(c.getDtFechamento() != null)
			throw new Exception("A última conta da mesa " + idMesa + " já foi fechada");
		
		c.addProduto(p);
		
		//if(!m.isOcupada()) {
			//throw new Exception("A mesa " + idMesa + " está sem clientes");
		//}else {
			//m.getUltimaConta().addProduto(p);
		//}
		
	}
	
	public static Conta consultarConta(int idMesa) throws Exception {
		/* Primeiro testa se a mesa em questão existe, depois testa se ela realmente tem uma conta. */
		
		Mesa m = r.localizarMesa(idMesa);
		//if(m == null)
			//throw new Exception("A mesa " + idMesa + " não existe no sistema");
		
		Conta c = m.getUltimaConta();
		//if(c == null)
			//throw new Exception("Não existe nenhuma conta na mesa " + idMesa);
		return c;
	}
	
	public static void fecharConta(int idMesa) throws Exception {
		/* Primeiro testa se a mesa em questão existe, depois testa se ela realmente tem uma conta e
		 * se essa conta está aberta. */
		
		Mesa m = r.localizarMesa(idMesa);
		if(m == null)
			throw new Exception("A mesa " + idMesa + " não existe no sistema");
		
		if(m.getGarcom() == null)
			throw new Exception("A mesa " + idMesa + " está sem garçom");
		
		Conta c = m.getUltimaConta();
		if(c == null)
			throw new Exception("Não existe nenhuma conta na mesa " + idMesa);
		
		if(c.getDtFechamento() != null)
			throw new Exception("A última conta da mesa " + idMesa + " já foi fechada");
		
		c.setDtFechamento(LocalDate.now());
		
		//if(!m.isOcupada())
			//throw new Exception("A mesa " + idMesa + " está sem clientes");
		//else
			//c.setDtFechamento(LocalDate.now());
		//
	}
	
	public static double calcularGorjeta(String apelido) throws Exception {
		/* Primeira testa se o garçom em questão existe no sistema, depois testa se ele possui mesas
		 * em atendimento. */
		
		/* AJEITAR ESSE MÉTODO, A GORJETA TEM QUE SER CALCULADA APENAS COM AS CONTAS DO DIA ATUAL!!! */
		
		Garcom g = r.localizarGarcom(apelido);
		if(g == null)
			throw new Exception("O garçom " + apelido + " não existe no sistema");
		
		ArrayList<Mesa> mesas = g.getMesas();
		if(mesas.isEmpty())
			throw new Exception("O garçom " + apelido + " não tem mesas");
		
		double gorjeta = 0;
		boolean naoTemPagamentos = true;
		
		for(Mesa m: mesas) {
			for(Conta c: m.getContas())
				if(c.getPagamento() != null) {
					gorjeta += c.getPagamento().calcularGorjeta();
					naoTemPagamentos = false;
				}
		}
		
		if(naoTemPagamentos)
			return 0;
			//throw new Exception("O garçom " + apelido + " não tem contas pagas");
		// gorjeta = 10% de total
		return gorjeta;
	}
	
	public static ArrayList<Conta> listarContas() {
		return r.getContas();
	}
	
	public static void cancelarConta(int idMesa) throws Exception {
		/* Primeiro testa se a mesa em questão existe, depois testa se ela tem alguma conta. Por fim 
		 * testa se a última conta já foi fechada. */
		
		Mesa m = r.localizarMesa(idMesa);
		if(m == null)
			throw new Exception("A mesa " + idMesa + " não existe no sistema");
		
		if(m.getGarcom() == null)
			throw new Exception("A mesa " + idMesa + " está sem garçom");
		
		Conta c = m.getUltimaConta();
		if(c == null)
			throw new Exception("Não existe nenhuma conta na mesa " + idMesa);
		
		if(c.getDtFechamento() != null)
			throw new Exception("A última conta da mesa " + idMesa + " já foi fechada");
		
		m.setOcupada(false);
		m.getContas().remove(c); //tirando a conta da mesa
		r.remConta(c); //tirando a conta do restaurante
	}
	
	public static void transferirConta(int idMesaOrigem, int idMesaDestino) throws Exception {
		/* Primeiro testa se as duas mesas existem, depois testa se a ultima conta da mesa de origem
		 * está aberta. Por fim verifica se a mesa destino está livre, se estiver, transfira. */
		
		if(idMesaOrigem == idMesaDestino)
			throw new Exception("Escolha mesas diferentes");
		
		Mesa m1 = r.localizarMesa(idMesaOrigem);
		if(m1 == null)
			throw new Exception("A mesa " + idMesaOrigem + " não existe no sistema");
		
		Mesa m2 = r.localizarMesa(idMesaDestino);
		if(m2 == null)
			throw new Exception("A mesa " + idMesaDestino + " não existe no sistema");
		
		if(m1.getGarcom() == null)
			throw new Exception("A mesa " + m1.getId() + " está sem garçom");
		
		if(m2.getGarcom() == null)
			throw new Exception("A mesa " + m2.getId() + " está sem garçom");
		
		if(!m1.getGarcom().getApelido().equals(m2.getGarcom().getApelido()))
			throw new Exception("As mesas "+m1.getId()+" e "+m2.getId()+" têm garçons diferentes");
		
		Conta c1 = m1.getUltimaConta();
		if(c1 == null)
			throw new Exception("Não existe nenhuma conta na mesa " + idMesaOrigem);
		
		if(c1.getDtFechamento() != null)
			throw new Exception("A última conta da mesa " + idMesaOrigem + " já foi fechada");
		
		Conta c2 = m2.getUltimaConta();
		//if(c2 != null)
			//if(c2.getDtFechamento() == null && c2.getPagamento() == null)
				//throw new Exception("A mesa " + idMesaDestino + " já está ocupada");
		
		if(c2 == null)
			throw new Exception("A mesa " + idMesaDestino + " não tem contas");
		
		if(c2.getDtFechamento() != null)
			throw new Exception("A mesa " + idMesaDestino + " está com a ultima conta fechada");
		
		//c2 = new Conta(m2);
		//r.addConta(c2);
		//m2.addConta(c2);
		m2.setOcupada(true);		
		for(Produto p: c1.getProdutos())
			c2.addProduto(p);
		
		//c2.getProdutos().addAll(c1.getProdutos());
		
		m1.getContas().remove(c1);
		r.remConta(c1);
		m1.setOcupada(false);
		
	}
	
	public static Pagamento pagarConta(int idMesa, String tipo, int percentual, String cartao, int qtd) throws Exception {
		/* Primeira testa se a mesa existe. */
		
		Mesa m = r.localizarMesa(idMesa);
		if(m == null)
			throw new Exception("A mesa " + idMesa + " não existe no sistema");
		
		if(m.getGarcom() == null)
			throw new Exception("A mesa " + idMesa + " está sem garçom");
		
		if(!tipo.equals("dinheiro") && !tipo.equals("cartao"))
			throw new Exception("O tipo de pagamento precisa ser 'dinheiro' ou 'cartao'");
		
		Conta c = m.getUltimaConta();
		if(c == null)
			throw new Exception("Não existe nenhuma conta na mesa " + idMesa);
		
		if(c.getDtFechamento() == null)
			throw new Exception("A última conta da mesa " + idMesa + " está aberta");
		
		if(c.getPagamento() != null)
			throw new Exception("Essa conta já foi paga");
		
		if(tipo.equals("dinheiro")) {
			//percentual = percentual de desconto
			if(percentual < 0 || percentual > 5)
				throw new Exception("Percentual de desconto fora do intervalo permitido");
			
			m.setOcupada(false);
			PagamentoDinheiro pd = new PagamentoDinheiro();
			pd.setPercentualDesconto(percentual);
			pd.calcularPagamento(c.getTotal());
			c.setPagamento(pd);
			return pd;
			
		}else {
			//qtd = quantidade de parcelas
			if(qtd < 1 || qtd > 4)
				throw new Exception("A quantidade de parcelas precisa ser de 1 a 4");
			
			if(qtd > 1 && c.getTotal()/qtd < 100)
				throw new Exception("O valor mínimo de uma parcela deve ser 100 quando há mais de uma");
			
			m.setOcupada(false);
			PagamentoCartao pc = new PagamentoCartao();
			pc.setQuantidadeParcelas(qtd);
			pc.setCartao(cartao);
			pc.calcularPagamento(c.getTotal());
			c.setPagamento(pc);
			return pc;
			
		}
		
	}
	
	public static void excluirGarcom(String apelido) throws Exception {
		
		Garcom g = r.localizarGarcom(apelido);
		if(g == null)
			throw new Exception("O garçom " + apelido + " não existe no sistema");
		
		for(Mesa m: g.getMesas())
			m.setGarcom(null);
		
		r.remGarcom(g);
	}
	
	public static double calcularPercentualMedio(String apelido) throws Exception {
		
		Garcom g = r.localizarGarcom(apelido);
		if(g == null)
			throw new Exception("O garçom " + apelido + " não existe no sistema");
		
		ArrayList<Mesa> mesas = g.getMesas();
		
		if(mesas.isEmpty())
			throw new Exception("O Garçom " + apelido + " não tem nenhuma mesa");
		
		double total = 0, qtd = 0;
		
		for(Mesa m: mesas) {
			for(Conta c: m.getContas()) {
				if(c.getPagamento() instanceof PagamentoDinheiro) {
					PagamentoDinheiro pd = (PagamentoDinheiro) c.getPagamento();
					total += pd.getPercentualDesconto();
					qtd++;
				}
			}
		}
		
		if(qtd == 0)
			throw new Exception("Não há nenhuma conta paga com " + apelido);
			
		return total/qtd;
		
	}

}

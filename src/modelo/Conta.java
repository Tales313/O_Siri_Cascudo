package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Conta {
	
	private static int idTotal;
	private int numero;
	private LocalDate dtFechamento;
	private Mesa mesa;
	private ArrayList<Produto> produtos;
	private Pagamento pagamento;
	
	public Conta(Mesa m) {
		idTotal++;
		numero = idTotal;
		mesa = m;
		produtos = new ArrayList<>();
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void addProduto(Produto p) {
		produtos.add(p);
	}

	public double getTotal() {
		double t = 0;
		
		for(Produto p: produtos)
			t += p.getPreco();
		
		return t;
	}

	public int getNumero() {
		return numero;
	}

	public LocalDate getDtFechamento() {
		return dtFechamento;
	}
	
	public void setDtFechamento(LocalDate d) {
		dtFechamento = d;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setPagamento(Pagamento p) {
		this.pagamento = p;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	@Override
	public String toString() {
		
		String s = "Conta [" + getNumero() + ", ";
		
		if(dtFechamento != null) {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			s += "Fechamento=" + dtFechamento.format(f) + ", ";
		}
		
		s += "precoTotal=" + getTotal() + ", Mesa=" + mesa.getId() + ", produtos=" + produtos;
		
		if(pagamento != null)
			s += ", pagamento=" + pagamento + "]";
		else
			s += "]";
		
		return s;
				
	}

}

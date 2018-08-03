package modelo;

public class PagamentoDinheiro extends Pagamento {
	
	//protected double valorPago;
	private int percentualDesconto;

	@Override
	public void calcularPagamento(double total) {
		valorPago = total - (total*percentualDesconto)/100;	
	}

	public void setPercentualDesconto(int pd) {
		this.percentualDesconto = pd;
	}

	public int getPercentualDesconto() {
		return percentualDesconto;
	}

	@Override
	public String toString() {
		return "PagamentoDinheiro [valorPago=" + valorPago + ", pctDesconto=" + percentualDesconto + "]";
	}

}

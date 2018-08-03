package modelo;

public abstract class Pagamento {
	
	protected double valorPago;
	
	public abstract void calcularPagamento(double total);
	
	public double calcularGorjeta() {
		return valorPago/10;
	}

	public double getValorPago() {
		return valorPago;
	}
	
}

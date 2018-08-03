package modelo;

public class PagamentoCartao extends Pagamento {
	
	//protected double valorPago;
	private String cartao;
	private int quantidadeParcelas;

	@Override
	public void calcularPagamento(double total) {
		
		if(quantidadeParcelas == 3)
			total *= 1.1; //acrescimo de 10% no valor total
		else if(quantidadeParcelas == 4)
			total *= 1.2; //acrescimo de 20% no valor total
		
		valorPago = total;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	@Override
	public String toString() {
		return "PagamentoCartao [valorPago=" + valorPago + ", qtdParcelas=" + quantidadeParcelas + ", cartao="
				+ cartao + "]";
	}

}

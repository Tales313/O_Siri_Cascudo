package modelo;

public class Produto implements Comparable<Produto> {
	
	private String nome;
	private double preco;
	
	public Produto(String n, double p) {
		nome = n;
		preco = p;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	@Override
	public String toString() {
		return "Produto [" + nome + ", " + preco + "]";
	}

	@Override
	public int compareTo(Produto outro) {
		return this.getNome().compareTo(outro.getNome());
	}

}

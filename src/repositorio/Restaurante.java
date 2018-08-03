package repositorio;

import java.util.ArrayList;
import java.util.TreeMap;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Produto;

public class Restaurante {
	
	private String nome;
	private ArrayList<Produto> produtos;
	private ArrayList<Mesa> mesas;
	private ArrayList<Conta> contas;
	private TreeMap<String, Garcom> garcons;
	
	public Restaurante(String n) {
		nome = n;
		produtos = new ArrayList<>();
		mesas = new ArrayList<>();
		contas = new ArrayList<>();
		garcons = new TreeMap<>();
	}
	
	public String getNome() {
		return nome;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void addProduto(Produto p) {
			produtos.add(p);
	}
	
	public void remProduto(String n) {
		Produto p = localizarProduto(n);
		produtos.remove(p);
	}
	
	public Produto localizarProduto(String n) {
		/* Procura um produto pelo seu nome especifico, se achar retorna ele, senão retorna nulo */
		for(Produto p: produtos)
			if(p.getNome().equals(n))
				return p;
		return null;
	}
	
	public ArrayList<Produto> localizarProdutos(String n) {
		/* Procura produtos pelo nome dado. Exemplo: pe (peixe, peito de frango) */
		/* Caso o parâmetro seja uma String vazia, retorna todos os produtos */
		ArrayList<Produto> retorno = new ArrayList<>();
		if(n.equals("")) {
			retorno.addAll(produtos);
		}else {
			for(Produto p: produtos)
				if(p.getNome().contains(n))
					retorno.add(p);
		}
		return retorno;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	public void addMesa(Mesa m) {
		mesas.add(m);
	}
	
	public Mesa localizarMesa(int id) {
		/* Procura uma mesa pelo seu id, se achar retorna ela, senão retorna nulo. */
		for(Mesa m: mesas)
			if(m.getId() == id)
				return m;
		return null;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void addConta(Conta c) {
		contas.add(c);
	}
	
	public void remConta(Conta c) {
		contas.remove(c);
	}

	public ArrayList<Garcom> getGarcons() {
		return new ArrayList<Garcom>(garcons.values());
	}

	public void addGarcom(Garcom g) {
		garcons.put(g.getApelido(), g);
	}
	
	public void remGarcom(Garcom g) {
		garcons.remove(g.getApelido());
	}
	
	public Garcom localizarGarcom(String apelido) {
		for(Garcom g: garcons.values())
			if(g.getApelido().equals(apelido))
				return g;
		return null;
	}
	
}

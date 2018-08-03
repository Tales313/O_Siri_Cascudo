package modelo;

import java.util.ArrayList;

public class Mesa {
	
	private static int total;
	private int id;
	private boolean ocupada;
	private ArrayList<Conta> contas;
	private Garcom garcom;
	
	public Mesa() {
		total++;
		id = total;
		contas = new ArrayList<>();
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean o) {
		ocupada = o;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}
	
	public Conta getUltimaConta() {
		if(contas.isEmpty())
			return null;
		return contas.get(contas.size()-1);
	}

	public void addConta(Conta c) {
		contas.add(c);
	}

	public static int getTotal() {
		return total;
	}

	public int getId() {
		return id;
	}
	
	public void setGarcom(Garcom g) {
		garcom = g;
	}

	public Garcom getGarcom() {
		return garcom;
	}

	@Override
	public String toString() {
		String s = "Mesa [" + id + ", ";
		
		if(this.isOcupada())
			s += "ocupada]";
		else
			s += "livre]";
		
		return s;
	}

}

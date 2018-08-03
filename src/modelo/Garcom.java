package modelo;

import java.util.ArrayList;

public class Garcom implements Comparable<Garcom> {
	
	private String apelido;
	private ArrayList<Mesa> mesas;
	
	public Garcom(String a) {
		apelido = a;
		mesas = new ArrayList<>();
	}

	public String getApelido() {
		return apelido;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}
	
	public void addMesa(Mesa nova) {
		mesas.add(nova);
	}
	
	public Mesa localizarMesa(int id) {
		/* Procura a mesa pelo id, se não achar retorna nulo */
		for(Mesa m: mesas)
			if(m.getId() == id)
				return m;
		return null;
	}
	
	@Override
	public int compareTo(Garcom outro) {
		return this.getApelido().compareTo(outro.getApelido());
	}

	@Override
	public String toString() {
		String s = "Garcom [" + apelido + ", ";
		
		for(Mesa m: mesas)
			s += m.getId() + ", ";
		s += "]";
		
		return s;
	}

}

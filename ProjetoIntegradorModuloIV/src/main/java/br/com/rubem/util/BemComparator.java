package br.com.rubem.util;

import java.util.Comparator;

import br.com.rubem.model.Bem;

public class BemComparator implements Comparator<Bem> {

	public BemComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Bem o1, Bem o2) {
		
		if(o1.getDepreciacao().getGanhoperda() == null) {
			return -1;
		}
		
		if(o1.getDepreciacao().getGanhoperda() == o2.getDepreciacao().getGanhoperda()) {
			return 0;
		}
		
		if(o1.getDepreciacao().getGanhoperda() < o2.getDepreciacao().getGanhoperda()) {
			return -1;
		}
		
		if(o1.getDepreciacao().getGanhoperda() > o2.getDepreciacao().getGanhoperda()) {
			return 1;
		}
		
		return 0;
	}

}

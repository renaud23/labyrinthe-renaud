package com.renaud.laby.worm;

import com.renaud.laby.game.Chrono;
import com.renaud.laby.worm.comportement.IComportement;

public class Indifferent implements IComportement{
	
	private Worm w;
	private int dir;
	private Chrono ch = new Chrono(50); 

	public Indifferent(Worm w) {
		this.w = w;
		this.dir = -1;
	}

	@Override
	public void activate() {
		if (ch.isEllapsed()) {
			try {
				dir = w.getMove().next();
			}
			catch (WormBlockedException e) {
				dir = 0;
				// blocked = true;
				this.w.reset();
			}
			int[] positions = w.getPositions();
			for (int i = w.getLength() - 1; i > 0; i--) {
				positions[i] = positions[i - 1];
			}
//			if (dir != 0)
//				pas++;
			positions[0] += dir;
		}
		
	}

}

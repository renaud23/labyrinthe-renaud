package com.renaud.laby.worm.comportement;

import com.renaud.laby.game.Chrono;
import com.renaud.laby.worm.Worm;
import com.renaud.laby.worm.WormBlockedException;

public class Indifferent implements IComportement{
	
	private Worm w;
	private int dir;
	private Chrono ch; 

	public Indifferent(Worm w,long speed) {
		this.w = w;
		this.dir = -1;
		ch = new Chrono(speed);
	}

	@Override
	public void activate() {
		if (ch.isEllapsed()) {
			try {
				dir = w.getMouvement().next();
			}
			catch (WormBlockedException e) {
				dir = 0;
				this.w.reset();
			}
			int[] positions = w.getPositions();
			for (int i = w.getLength() - 1; i > 0; i--) {
				positions[i] = positions[i - 1];
			}
			if (dir != 0)
				w.incrementPas();
			positions[0] += dir;
		}
		
	}

}

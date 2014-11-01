package com.renaud.laby.worm.comportement;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.LabyrintheTools;
import com.renaud.laby.game.Chrono;
import com.renaud.laby.player.Player;
import com.renaud.laby.worm.Worm;
import com.renaud.laby.worm.WormBlockedException;

public class Suiveur implements IComportement{
	
	private Labyrinthe l;
	private Player p;
	private Worm w;
	private int dir;
	private Chrono ch; 
	
	public Suiveur(Labyrinthe l,Player p,Worm w,long speed) {
		this.w = w;
		this.dir = -1;
		this.l = l;
		ch = new Chrono(speed);
	}

	@Override
	public void activate() {
		if (ch.isEllapsed()) {
			
			this.search();
			
			
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
	
	
	private void search(){
		int or = this.w.getMouvement().getOrientation();
		
		if(or > 0){
			int[] walls = l.getTable();
			
			int distance = 10;
			int i = 1;
			
			int nextPos = LabyrintheTools.nextPos(l, or, w.getPositions()[0], i);
			while(i<distance && nextPos != LabyrintheTools.BLOCKED){
				System.out.print(nextPos+" ");
				
				
				i++;
				nextPos = LabyrintheTools.nextPos(l, or, w.getPositions()[0], i);
			}
			System.out.println(" "+i);
		}
		
	}

}

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
	private int variation;
	private Chrono ch; 
	private int positionJoueur;
	private boolean find;
	
	public Suiveur(Labyrinthe l,Player p,Worm w,long speed) {
		this.w = w;
		this.variation = -1;
		this.l = l;
		this.p = p;
		ch = new Chrono(speed);
	}

	@Override
	public void activate() {
		if (ch.isEllapsed()) {
			boolean look = this.search(this.w.getMouvement().getOrientation());
			if(!look) look = this.search(LabyrintheTools.getRightDirection(this.w.getMouvement().getOrientation()));
			if(!look) look = this.search(LabyrintheTools.getLeftDirection(this.w.getMouvement().getOrientation()));
			
			if(look) find = true;
			if(find){
				variation = LabyrintheTools.getVariation(w.getPositions()[0], positionJoueur, l.getLargeurTable());
				System.out.println(variation);
				if(w.getPositions()[0] == positionJoueur) find = false;
			}else{
				try {
					variation = w.getMouvement().next();
				}
				catch (WormBlockedException e) {
					variation = 0;
					this.w.reset();
				}
			}
			
			int[] positions = w.getPositions();
			for (int i = w.getLength() - 1; i > 0; i--) {
				positions[i] = positions[i - 1];
			}
			if (variation != 0)
				w.incrementPas();
			positions[0] += variation;
		}// if
	}
	
	
	private boolean search(int directionRegard){
		int or = directionRegard;//this.w.getMouvement().getOrientation();
		
		boolean find = false;
		if(or > 0){
			int[] walls = l.getTable();
			
			int distance = 10;
			int i = 1;
			
			int nextPos = LabyrintheTools.nextPos(l, or, w.getPositions()[0], i);
			while(i<distance && nextPos != LabyrintheTools.BLOCKED && walls[nextPos]==0 && !find){
				if(p.getPosition() == nextPos){
					find = true;
				}
				
				i++;
				nextPos = LabyrintheTools.nextPos(l, or, w.getPositions()[0], i);
			}
		}
		
		if(this.w.getPositions()[0] == this.p.getPosition()) find = true;
		if(find){
			this.positionJoueur = p.getPosition();
//			this.w.getMouvement().reset();
//			
//			if(this.w.getPositions()[0] == this.p.getPosition()) variation = 0;
//			else variation = LabyrintheTools.nextPos(l, or, w.getPositions()[0], 1)- w.getPositions()[0] ;
//			
		}
		
		return find;
	}

}

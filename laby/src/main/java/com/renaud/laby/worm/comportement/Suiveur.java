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
	private boolean poursuite;
	
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
			if(!look && variation == 0) look = this.search(LabyrintheTools.getBackDirection(this.w.getMouvement().getOrientation()));
			
			if(look){
				poursuite = true;
				this.positionJoueur = p.getPosition();
				this.w.reset();
			}
			if(poursuite){
				if(w.getPositions()[0] == positionJoueur){
					poursuite = false;
					variation = 0;
				}else variation = LabyrintheTools.getVariation(w.getPositions()[0], positionJoueur, l.getLargeurTable());
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
		boolean vue = false;
		if(this.w.getPositions()[0] == this.p.getPosition()) vue = true;
		else if(directionRegard > 0){
			int[] walls = l.getTable();
			
			int distance = 10;
			int i = 1;
			
			int nextPos = LabyrintheTools.nextPos(l, directionRegard, w.getPositions()[0], i);
			while(i<distance && nextPos != LabyrintheTools.BLOCKED && walls[nextPos]==0 && !vue){
				if(p.getPosition() == nextPos){
					vue = true;
				}
				
				i++;
				nextPos = LabyrintheTools.nextPos(l, directionRegard, w.getPositions()[0], i);
			}
		}
		return vue;
	}

}

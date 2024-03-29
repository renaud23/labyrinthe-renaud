package com.renaud.laby.worm.mouvement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.renaud.laby.Direction;
import com.renaud.laby.Labyrinthe;
import com.renaud.laby.worm.Worm;

public class MarcheAuHazard implements IWormMouvement{
	
	private Labyrinthe laby;
	private Worm w;
	private int dir;
	
	private int orientation;
	

	public MarcheAuHazard(Labyrinthe laby, Worm w) {
		this.laby = laby;
		this.w = w;
	}



	@Override
	public int next() {
		int[] tab = laby.getTable();
		Random r = new Random();
		List<Integer> tmp = new ArrayList<Integer>();
		int l = w.getPositions()[0] % laby.getLargeurTable();
		int h = w.getPositions()[0] / laby.getLargeurTable();
//		System.out.println(l+" "+h+" "+positions[0]);
		if(l < (laby.getLargeurTable()-2) && tab[w.getPositions()[0]+1] == 0 && dir != -1){
			tmp.add(1);
		}
		if(l>1 && tab[w.getPositions()[0]-1] == 0 && dir != 1){
			tmp.add(-1);
		}
		if(h <(laby.getHauteurTable()-2) && tab[w.getPositions()[0]+laby.getLargeurTable()] == 0 && dir != -laby.getLargeurTable()){
			tmp.add(laby.getLargeurTable());
		}
		if(h>1 && tab[w.getPositions()[0]-laby.getLargeurTable()] == 0 && dir != laby.getLargeurTable()){
			tmp.add(-laby.getLargeurTable());
		}
		
		if(tmp.size()>0) dir = tmp.get(r.nextInt(tmp.size()));
		else dir = 0;
		
		this.checkOrientation();
		
		return dir;
	}

	private void checkOrientation(){
		if(dir == 1) orientation = Direction.EST;
		else if(dir == -1) orientation = Direction.OUEST;
		else if(dir == laby.getLargeurTable()) orientation = Direction.SUD;
		else if(dir == -laby.getLargeurTable()) orientation = Direction.NORD;
	}


	@Override
	public int getOrientation() {
		return this.orientation;
	}



	@Override
	public void reset() {
		// NOTHING
		
	}

}

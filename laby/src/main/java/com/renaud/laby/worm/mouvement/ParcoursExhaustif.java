package com.renaud.laby.worm.mouvement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.renaud.laby.Direction;
import com.renaud.laby.Labyrinthe;
import com.renaud.laby.worm.Worm;
import com.renaud.laby.worm.WormBlockedException;

public class ParcoursExhaustif extends MarcheAuHazardMemoire implements IWormMouvement {

	private Labyrinthe laby;
	private Worm w;
	private boolean reverse = false;

	private int dir;
	private int orientation;
	private Stack<Integer> dirs;

	public ParcoursExhaustif(Labyrinthe laby, Worm w) {
		super(laby, w);
		this.laby = laby;
		this.w = w;
		dirs = new Stack<>();
	}

	@Override
	public int next() {
		Random r = new Random();
		this.look();

		int l = w.getPositions()[0] % laby.getLargeurTable();
		int h = w.getPositions()[0] / laby.getLargeurTable();

		int find = 0;
		int count = 0;
		List<Integer> tmp = new ArrayList<>();

		if (l < (laby.getLargeurTable() - 2) && memory[w.getPositions()[0] + 1] == 0) {
			if(dir != -1){
				tmp.add(w.getPositions()[0] + 1);
				find++;
			}
			count++;
		}
		if (l > 1 && memory[w.getPositions()[0] - 1] == 0) {
			if(dir != 1){
				tmp.add(w.getPositions()[0] - 1);
				find++;
			}
			count++;
		}
		if (h < (laby.getHauteurTable() - 2) && memory[w.getPositions()[0] + laby.getLargeurTable()] == 0) {
			if(dir != -laby.getLargeurTable()){
				tmp.add(w.getPositions()[0] + laby.getLargeurTable());
				find++;
			}
			count++;
		}
		if (h > 1 && memory[w.getPositions()[0] - laby.getLargeurTable()] == 0) {
			if(dir != laby.getLargeurTable()){
				tmp.add(w.getPositions()[0] - laby.getLargeurTable());
				find++;
			}
			count++;
		}

		if (!reverse) {
			if (find != 0) {
				int where = tmp.get(r.nextInt(tmp.size()));
				if (count > 1)
					dirs.push(where);
				dir = where - w.getPositions()[0];
			}else {
				dir = 0;
				reverse = true;
			}
		}else {
			if (find > 1) {
				reverse = false;
				if (dirs.isEmpty()) {
					dir = 0;

				}else {

					int where = dirs.peek();
					while (where == dirs.peek())
						where = tmp.get(r.nextInt(tmp.size()));

					dir = where - w.getPositions()[0];
					dirs.push(w.getPositions()[0]);
					dirs.push(where);
				}
			}
			else if (!dirs.empty()) {
				int where = dirs.pop();
				dir = where - w.getPositions()[0];
			}else {
				dir = 0;
				reverse = false;
				if(find == 0) throw new WormBlockedException();
			}
		}
		this.checkOrientation();
		
		return dir;
	}
	
	
	private void checkOrientation(){
		if(dir == 1) orientation = Direction.EST;
		else if(dir == -1) orientation = Direction.OUEST;
		else if(dir == laby.getLargeurTable()) orientation = Direction.SUD;
		else if(dir == -laby.getLargeurTable()) orientation = Direction.NORD;
	}

	public int getOrientation() {
		return orientation;
	}
	
	

}

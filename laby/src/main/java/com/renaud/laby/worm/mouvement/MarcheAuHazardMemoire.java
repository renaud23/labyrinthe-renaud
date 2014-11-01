package com.renaud.laby.worm.mouvement;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.worm.Worm;
import com.renaud.laby.worm.WormBlockedException;

public class MarcheAuHazardMemoire implements IWormMouvement {

	protected Labyrinthe laby;
	protected Worm w;
	protected int[] memory;
	protected int dir;

	public MarcheAuHazardMemoire(Labyrinthe laby, Worm w) {
		this.laby = laby;
		this.w = w;

		this.memory = new int[laby.getLargeurTable() * laby.getHauteurTable()];
	}

	@Override
	public int next() {
		this.look();

		Random r = new Random();
		List<Integer> tmp = new ArrayList<Integer>();
		int l = w.getPositions()[0] % laby.getLargeurTable();
		int h = w.getPositions()[0] / laby.getLargeurTable();

		if (l < (laby.getLargeurTable() - 2) && memory[w.getPositions()[0] + 1] == 0 && dir != -1) {
			tmp.add(1);
		}
		if (l > 1 && memory[w.getPositions()[0] - 1] == 0 && dir != 1) {
			tmp.add(-1);
		}
		if (h < (laby.getHauteurTable() - 2) && memory[w.getPositions()[0] + laby.getLargeurTable()] == 0 && dir != -laby.getLargeurTable()) {
			tmp.add(laby.getLargeurTable());
		}
		if (h > 1 && memory[w.getPositions()[0] - laby.getLargeurTable()] == 0 && dir != laby.getLargeurTable()) {
			tmp.add(-laby.getLargeurTable());
		}
		
		if(dir == 0 && tmp.isEmpty()) throw new WormBlockedException();
		else if (tmp.size() > 0) dir = tmp.get(r.nextInt(tmp.size()));
		else dir = 0;

		return dir;
	}

	protected void look() {
		int pos = w.getPositions()[0];
		int[] tab = laby.getTable();

		int nbMur = 0;
		if (tab[pos - laby.getLargeurTable()] == 1 || memory[pos - laby.getLargeurTable()] == 1) {
			memory[pos - laby.getLargeurTable()] = 1;
			nbMur++;
		}
		if (tab[pos + laby.getLargeurTable()] == 1 || memory[pos + laby.getLargeurTable()] == 1) {
			memory[pos + laby.getLargeurTable()] = 1;
			nbMur++;
		}
		if (tab[pos - 1] == 1 || memory[pos - 1] == 1) {
			memory[pos - 1] = 1;
			nbMur++;
		}
		if (tab[pos + 1] == 1 || memory[pos + 1] == 1) {
			memory[pos + 1] = 1;
			nbMur++;
		}

		if (nbMur > 2)
			memory[pos] = 1;

	}

	public void print(PrintStream out) {

		// print
		for (int i = 0; i < (laby.getLargeurTable() * laby.getHauteurTable()); i++) {
			if (w.getPositions()[0] == i)
				System.out.print("0");
			else
				if (this.memory[i] == 1)
					System.out.print("X");
				else
					if (this.memory[i] == 0)
						System.out.print(".");

			if ((i % laby.getLargeurTable()) == (laby.getLargeurTable() - 1))
				System.out.println();

		}
	}

}

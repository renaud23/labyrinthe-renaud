package com.renaud.laby.worm;

import java.awt.Color;
import java.util.Random;
import com.renaud.laby.Labyrinthe;
import com.renaud.laby.game.Chrono;
import com.renaud.laby.game.IActivate;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;

public class Worm implements IActivate, IDrawable, DrawOperationAware {

	private IDrawOperation op;
	private int pas;
	private boolean blocked = false;

	private Labyrinthe laby;
	private long speed;
	private int length = 10;
	private int[] positions;
	private int dir = -1;
	private Chrono ch = new Chrono(50);
	private IWormMouvement move;

	public Worm(Labyrinthe laby) {
		this.laby = laby;
		this.init();
		move = new ParcoursExhaustif(laby, this);
	}
	
	public Worm(Labyrinthe laby, int length, IWormMouvement move,long speed) {
		this.laby = laby;
		this.length = length;
		this.move = move;
		this.ch = new Chrono(this.speed = speed);
	}



	@Override
	public void activate() {
		if (ch.isEllapsed()) {

			// this.changeDirection();
			try{
				dir = this.move.next();
			}catch(WormBlockedException e){
				dir = 0;
				blocked = true;
				System.out.println(pas);
			}

			for (int i = length - 1; i > 0; i--) {
				positions[i] = positions[i - 1];
			}
			if(dir != 0) pas++;
			positions[0] += dir;
		}
	}

	private void init() {
		int[] tab = laby.getTable();
		Random rnd = new Random();
		int start = -1;
		while (start < 0) {
			int n = rnd.nextInt(laby.getLargeurTable() * laby.getHauteurTable());
			if (tab[n] == 0)
				start = n;
		}
		this.positions = new int[length];
		for (int i = 0; i < length; i++)
			positions[i] = -1;

		this.positions[0] = start;

	}

	@Override
	public boolean isChange() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		float alpha = 1.0f;
		for (int i = 1; i < length; i++) {
			if (i != -1) {
				alpha *= 0.9f;
				int l = this.positions[i] % laby.getLargeurTable();
				int h = this.positions[i] / laby.getLargeurTable();
				op.fillRect(Color.gray, 5 * l + 1, 5 * h + 1, 3, 3, alpha);
			}
		}

		int l = this.positions[0] % laby.getLargeurTable();
		int h = this.positions[0] / laby.getLargeurTable();
		op.fillRect(Color.red, 5 * l, 5 * h, 5, 5, 1.0f);

	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;

	}

	public int[] getPositions() {
		return positions;
	}

	public int getPas() {
		return pas;
	}

	@Override
	public boolean isActive() {
		return !this.blocked;
	}

	@Override
	public boolean isFinished() {
		return !this.blocked;
	}


}

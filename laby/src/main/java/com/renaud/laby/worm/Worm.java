package com.renaud.laby.worm;

import java.awt.Color;
import java.util.Random;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.game.Chrono;
import com.renaud.laby.game.IActivate;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;
import com.renaud.laby.worm.comportement.IComportement;
import com.renaud.laby.worm.comportement.Indifferent;
import com.renaud.laby.worm.mouvement.IWormMouvement;
import com.renaud.laby.worm.mouvement.ParcoursExhaustif;

public class Worm implements IActivate, IDrawable, DrawOperationAware {

	private IDrawOperation op;
	private int pas;
	private boolean blocked = false;

	private Labyrinthe laby;

	private int length = 10;
	private int[] positions;
	private int dir = -1;
	private Chrono ch = new Chrono(50);
	
	private IComportement comportement;
	private IWormMouvement mouvement;

	private int xDraw;
	private int yDraw;

	public Worm(Labyrinthe laby) {
		this.laby = laby;
		this.init();
		
		this.mouvement = new ParcoursExhaustif(laby, this);
		this.comportement = new Indifferent(this);
	}

	public Worm(Labyrinthe laby, int length, long speed) {
		this.laby = laby;
		this.length = length;
		this.mouvement = new ParcoursExhaustif(laby, this);
		this.comportement = new Indifferent(this);
		this.init();
		this.ch = new Chrono(speed);
	}

	@Override
	public void activate() {
//		if (ch.isEllapsed()) {
//			try {
//				dir = this.mouvement.next();
//			}
//			catch (WormBlockedException e) {
//				dir = 0;
//				// blocked = true;
//				this.mouvement = new ParcoursExhaustif(laby, this);
//			}
//
//			for (int i = length - 1; i > 0; i--) {
//				positions[i] = positions[i - 1];
//			}
//			if (dir != 0)
//				pas++;
//			positions[0] += dir;
//		}
		
		this.comportement.activate();
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
			if (this.positions[i] != -1) {
				alpha *= 0.9f;
				int l = this.positions[i] % laby.getLargeurTable();
				int h = this.positions[i] / laby.getLargeurTable();
				op.fillRect(Color.gray, xDraw + 5 * l + 1, yDraw + 5 * h + 1, 3, 3, alpha);
			}
		}

		int l = this.positions[0] % laby.getLargeurTable();
		int h = this.positions[0] / laby.getLargeurTable();
		op.fillRect(Color.red, xDraw + 5 * l, yDraw + 5 * h, 5, 5, 1.0f);

	}

	
	public void reset(){
		this.mouvement = new ParcoursExhaustif(laby, this);
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

	public int getxDraw() {
		return xDraw;
	}

	public void setxDraw(int xDraw) {
		this.xDraw = xDraw;
	}

	public int getyDraw() {
		return yDraw;
	}

	public void setyDraw(int yDraw) {
		this.yDraw = yDraw;
	}

	public IWormMouvement getMouvement() {
		return mouvement;
	}

	public int getLength() {
		return length;
	}

	public IComportement getComportement() {
		return comportement;
	}

	public void setComportement(IComportement comportement) {
		this.comportement = comportement;
	}

	public void setMouvement(IWormMouvement mouvement) {
		this.mouvement = mouvement;
	}

	
}

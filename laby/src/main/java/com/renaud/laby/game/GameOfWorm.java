package com.renaud.laby.game;

import java.awt.Color;
import java.util.List;

import com.renaud.laby.Direction;
import com.renaud.laby.Labyrinthe;
import com.renaud.laby.player.Player;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;
import com.renaud.laby.view.LabyDrawer;
import com.renaud.laby.view.LabyPlayerDrawer;
import com.renaud.laby.worm.Worm;

public class GameOfWorm extends Game implements IActivate, IDrawable, DrawOperationAware {

	private IDrawOperation op;
	private LabyDrawer ldrw;
	

	private Player p;
	private List<Worm> ws;
	private Labyrinthe l;

	private boolean onFirst;
	private boolean onSecond;

	public GameOfWorm(Player p, List<Worm> ws, Labyrinthe l) {
		this.p = p;
		this.ws = ws;
		this.l = l;

		ldrw = new LabyPlayerDrawer(p,l, 200, 200);

		for (Worm w : ws) {
			w.setxDraw(200);
			w.setyDraw(200);

			this.addActivable(w);
		}
		this.addActivable(p);
	}

	@Override
	public void activate() {
		super.activate();

		this.onFirst = false;
		this.onSecond = false;
		for (Worm w : ws) {
			int[] pw = w.getPositions();
			int pp = this.p.getPosition();
			int dir = this.p.getDirectionRegard();
			int lar = l.getLargeurTable();

			for (Integer pos : pw) {
				if (pos == pp) {
					this.onFirst = true;
				}
				else {
				if (dir == Direction.SUD && pos == (pp + lar))
					onSecond = true;
				else if (dir == Direction.NORD && pos == (pp - lar))
					onSecond = true;
				else if (dir == Direction.EST && pos == (pp + 1))
						onSecond = true;
				else if (dir == Direction.OUEST && pos == (pp - 1))
							onSecond = true;
				}
			}
		}// for w
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
		this.p.setDrawOperation(op);
		this.ldrw.setDrawOperation(op);
		this.ldrw.draw();
		for (Worm w : ws) {
			w.setDrawOperation(op);
			w.draw();
		}
		this.p.draw();

		// le ver sur la vue des joueurs
		if (this.onFirst) {
			this.op.fillCircle(Color.red, 50, 50, 40, 1.0f);
			this.op.fillCircle(Color.blue, 235, 50, 30, 1.0f);
		}
		else if (this.onSecond) {
			this.op.fillCircle(Color.red, 60, 50, 20, 1.0f);
			this.op.fillCircle(Color.blue, 245, 60, 10, 1.0f);
		}
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;
	}

}

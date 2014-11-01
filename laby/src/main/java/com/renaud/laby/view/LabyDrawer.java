package com.renaud.laby.view;

import java.awt.Color;

import com.renaud.laby.Labyrinthe;

public class LabyDrawer implements IDrawable, DrawOperationAware {

	protected IDrawOperation drawOperation;

	protected Labyrinthe laby;
	protected int x;
	protected int y;
	
	protected int dalleHauteur = 5;
	protected int dalleLargeur = 5;

	public LabyDrawer(Labyrinthe laby) {
		this.laby = laby;
	}

	public LabyDrawer(Labyrinthe laby, int x, int y) {
		this.laby = laby;
		this.x = x;
		this.y = y;
	}
	
	public LabyDrawer(Labyrinthe laby, int x, int y,int dl,int dh) {
		this.laby = laby;
		this.x = x;
		this.y = y;
		this.dalleHauteur = dh;
		this.dalleLargeur = dl;
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
		int[] t = laby.getTable();
		

		for (int i = 0; i < laby.getHauteurTable(); i++) {
			for (int j = 0; j < laby.getLargeurTable(); j++) {
				if (t[laby.getLargeurTable() * i + j] == 1)
					drawOperation.fillRect(Color.black, x + j * dalleLargeur, y + i * dalleHauteur, dalleLargeur, dalleHauteur, 1.0f);
				else
					drawOperation.fillRect(Color.yellow, x + j * dalleLargeur, y + i * dalleHauteur, dalleLargeur, dalleHauteur, 1.0f);
			}
		}

	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.drawOperation = op;
	}

}

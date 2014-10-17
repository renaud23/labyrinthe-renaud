package com.renaud.laby.view;

import java.awt.Color;

import com.renaud.laby.Labyrinthe;

public class LabyDrawer implements IDrawable, DrawOperationAware {

	private IDrawOperation drawOperation;

	private Labyrinthe laby;

	public LabyDrawer(Labyrinthe laby) {
		this.laby = laby;
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
		int h = 5;
		int l = 5;
		
		for(int i=0;i<laby.getHauteurTable();i++){
			for(int j=0;j<laby.getLargeurTable();j++){
				if(t[laby.getLargeurTable()*i+j] == 1) drawOperation.fillRect(Color.black, j*l, i*h, l, h, 1.0f);
				else drawOperation.fillRect(Color.yellow, j*l, i*h, l, h, 1.0f);
			}
		}

	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.drawOperation = op;
	}

}

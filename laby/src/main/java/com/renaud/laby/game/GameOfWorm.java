package com.renaud.laby.game;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.player.Player;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;
import com.renaud.laby.worm.Worm;

public class GameOfWorm extends Game implements IDrawable,DrawOperationAware{
	
	private Player p;
	private Worm w;
	private Labyrinthe l;
	
	
	public void activate(){
		super.activate();
	}


	@Override
	public void setDrawOperation(IDrawOperation op) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	

}

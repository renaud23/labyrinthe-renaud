package com.renaud.laby.player;

import java.util.Random;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.IController;
import com.renaud.laby.game.IActivate;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;

public class Player implements IController,IActivate, IDrawable, DrawOperationAware{
	

	private IDrawOperation op;
	
	private Labyrinthe laby;
	private int pos = 1;
	
	private Random r = new Random();
	
	@Override
	public void activate() {
		int t[] = laby.getTable();
		
		while(pos == 1){
			pos = t[r.nextInt(t.length)];
		}
		
		
	}
	
	
	// Controller
	
	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;
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

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

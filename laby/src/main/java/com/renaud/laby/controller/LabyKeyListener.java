package com.renaud.laby.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class LabyKeyListener extends KeyAdapter{
	
	private IController controllers;

	public LabyKeyListener(IController controllers) {
		this.controllers = controllers;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_UP){
			this.controllers.up();
		}else if(c == KeyEvent.VK_DOWN){
			this.controllers.down();
		}else if(c == KeyEvent.VK_LEFT){
			this.controllers.left();
		}else if(c == KeyEvent.VK_RIGHT){
			this.controllers.right();
		}else if(c == KeyEvent.VK_SHIFT && e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT){
			this.controllers.turnLeft();
		}else if(c == KeyEvent.VK_SHIFT && e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT){
			this.controllers.turnRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyReleased(e);
	}

}

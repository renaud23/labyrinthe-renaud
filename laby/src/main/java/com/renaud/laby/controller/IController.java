package com.renaud.laby.controller;

public interface IController {
	public void up();
	public void left();
	public void down();
	public void right();
	
	public void turnRight();
	public void turnLeft();
	
	public void mouseMoved(int x,int y);
}

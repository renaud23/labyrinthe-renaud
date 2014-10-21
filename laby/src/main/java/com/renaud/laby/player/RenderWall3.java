package com.renaud.laby.player;

import java.awt.Color;
import java.awt.Point;

import com.renaud.laby.view.IDrawOperation;

public class RenderWall3 {
	
	
	
	
	private int sx = 300;
	private int sy = 100;
	private int h = 100;
	private double lim = 200;
	
	public void render(IDrawOperation op,int code){
		Point[] a ={ 
				new Point(-50, 0) 
				,new Point(-50, 100) 
				,new Point(-50, 200),
				new Point(50, 0) 
				,new Point(50, 100) 
				,new Point(50, 200)} ;
		double lim = 400;
		for(Point p : a){
			double x = p.x;
			double y = p.y;
			
			double d = p.y;
			x = p.x -  p.x / lim * d;
			y = p.y *  Math.abs(p.x) / lim;

			
			
			op.drawRect(Color.red, sx+(int)x, sy-(int)y, 2, 2);
		}
		
	}
	
	
	private int dist(Point a, Point b){
		int d = (int) Math.pow(a.x-b.x,2)+(int) Math.pow(a.y-b.y,2);
		
		return (int) Math.sqrt(d);
	}
	
	private int distOrigine(Point a){
		int d = a.x*a.x+a.y*a.y;
		
		return (int) Math.sqrt(d);
	}

}

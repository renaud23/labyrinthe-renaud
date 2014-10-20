package com.renaud.laby.player;

import java.awt.Color;
import java.awt.Point;

import com.renaud.laby.view.IDrawOperation;

public class RenderWall3 {
	
	
	
	
	private int sx = 300;
	private int sy = 100;
	private int h = 100;
	private double lim = 20;
	
	public void render(IDrawOperation op,int code){
		Point[] a ={ 
				new Point(-25, 0) 
				,new Point(-25, 50) 
				,new Point(-25, 100),
				new Point(25, 0) 
				,new Point(25, 50) 
				,new Point(25, 100)} ;
		
		for(Point p : a){
			double x = p.x;
			double y = p.y;
			double d = distOrigine(p);
			double cx = x / lim;
			double cy = 1 + y / lim;
			x -= d*cx;
			y -= 1 / d*cy;
			
			
			op.drawRect(Color.red, sx-(int)p.x, sy-p.y, 2, 2);
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

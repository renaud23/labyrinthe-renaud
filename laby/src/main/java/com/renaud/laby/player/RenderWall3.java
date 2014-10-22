package com.renaud.laby.player;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.renaud.laby.view.IDrawOperation;

public class RenderWall3 {
	
	
	public class Point3D{
		int x,y,z;

		public Point3D(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
	}
	
	private Point3D[] WALL_HAUT = {new Point3D(-50,0,0),new Point3D(-50,50,0),new Point3D(50,50,0),new Point3D(50,0,0)};
	private Point3D[] WALL_GAUCHE = {new Point3D(-50,0,0),new Point3D(-50,50,0),new Point3D(-50,50,50),new Point3D(-50,0,50)};
	private Point3D[] WALL_DROITE = {new Point3D(50,0,0),new Point3D(50,50,0),new Point3D(50,50,50),new Point3D(50,0,50)};
	
	
	private int sx = 250;
	private int sy = 150;

	
	public void render(IDrawOperation op,int code){
		
		boolean g = (code & Player.GAUCHE_DIR) == Player.GAUCHE_DIR;
		boolean d = (code & Player.DROITE_DIR) == Player.DROITE_DIR;
		boolean h = (code & Player.HAUT_DIR) == Player.HAUT_DIR;
		boolean d1 = (code & Player.DROITE_DIR1) == Player.DROITE_DIR1;
		boolean g1 = (code & Player.GAUCHE_DIR1) == Player.GAUCHE_DIR1;
		boolean h1 = (code & Player.HAUT_DIR1) == Player.HAUT_DIR1;
		
		if(!h){
			this.drawWAll(op, WALL_HAUT, 50, 0);
			if(g) this.drawWAll(op, WALL_HAUT, 50, -100);
			if(d) this.drawWAll(op, WALL_HAUT, 50, 100);
		}else{
			if(!h1) this.drawWAll(op, WALL_HAUT, 100, 0);
			if(!g1) this.drawWAll(op, WALL_GAUCHE, 50, 0); else this.drawWAll(op, WALL_HAUT, 100, -100);
			if(!d1) this.drawWAll(op, WALL_DROITE, 50, 0); else this.drawWAll(op, WALL_HAUT, 100, 100);
		}
		
		if(!g) {
			if(h && g1) this.drawWAll(op, WALL_HAUT, 50, -100);
			this.drawWAll(op, WALL_GAUCHE, 0, 0);
		}else if(!g1) this.drawWAll(op, WALL_HAUT, 50, -100);
		if(!d) {
			if(h && d1)this.drawWAll(op, WALL_HAUT, 50, 100);
			this.drawWAll(op, WALL_DROITE, 0, 0);
		}else if(!d1) this.drawWAll(op, WALL_HAUT, 50, 100);
		

		
	}
	

	private void drawWAll(IDrawOperation op,Point3D[] wall,int dist,int dx){
		
		Point3D a = new Point3D(0,0,0);
		Point3D b = new Point3D(0,0,0);
		for(int i=0;i<4;i++){
			int j = (i == 3) ? 0 :i+1;

			a.x = wall[i].x + dx;
			a.y = wall[i].y;
			a.z = wall[i].z;
			b.x = wall[j].x + dx;
			b.y = wall[j].y;
			b.z = wall[j].z;

			checkPoint(a, dist);
			checkPoint(b, dist);
			
			op.drawLine(Color.black, sx+a.x, sy-a.y, sx+b.x, sy-b.y, 1.0f);
			op.drawRect(Color.red, sx+a.x-1, sy-a.y-1, 2, 2);
		}
		
	}
	
	
	private void checkPoint(Point3D p,int dist){
		double lim = 150;
		double limy = 25;
		
		double z = p.z + dist;
		
		double x = p.x * (1-1 / lim * z);
		double y = (p.y-limy) / p.x * x + limy;

		p.x = (int) x;
		p.y = (int) y;
	}

}

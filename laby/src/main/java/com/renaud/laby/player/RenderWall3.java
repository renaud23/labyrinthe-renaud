package com.renaud.laby.player;

import java.awt.Color;
import com.renaud.laby.view.IDrawOperation;

public class RenderWall3 {
	
	private Color color_wall = Color.gray;
	private Color color_wall_dark = Color.gray;
	private Color color_ground = Color.yellow;
	private Color color_stair = Color.lightGray;
	
	private final static int largeur_mur = 50;
	private final static int hauteur_mur = 80;
	
	
	public class Point3D{
		int x,y,z;

		public Point3D(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
	}
	
	private Point3D[] WALL_HAUT = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,0,0)};
	private Point3D[] WALL_GAUCHE = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,hauteur_mur,0),new Point3D(-largeur_mur/2,hauteur_mur,largeur_mur),new Point3D(-largeur_mur/2,0,largeur_mur)};
	private Point3D[] WALL_DROITE = {new Point3D(largeur_mur/2,0,0),new Point3D(largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,hauteur_mur,largeur_mur),new Point3D(largeur_mur/2,0,largeur_mur)};
	private Point3D[] GROUND = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,0,largeur_mur),new Point3D(largeur_mur/2,0,largeur_mur),new Point3D(largeur_mur/2,0,0)};
	
	
	private int sx = 250;
	private int sy = 100;

	
	public void render(IDrawOperation op,int code){
		
		boolean g = (code & Player.GAUCHE_DIR) == Player.GAUCHE_DIR;
		boolean d = (code & Player.DROITE_DIR) == Player.DROITE_DIR;
		boolean h = (code & Player.HAUT_DIR) == Player.HAUT_DIR;
		boolean d1 = (code & Player.DROITE_DIR1) == Player.DROITE_DIR1;
		boolean g1 = (code & Player.GAUCHE_DIR1) == Player.GAUCHE_DIR1;
		boolean h1 = (code & Player.HAUT_DIR1) == Player.HAUT_DIR1;
		
		this.drawWAll(color_ground, op, GROUND, 0, 0, 0);
		this.drawWAll(color_stair, op, GROUND, 0, 0, hauteur_mur);
		if(h){
			this.drawWAll(color_ground, op, GROUND, largeur_mur, 0, 0);
			this.drawWAll(color_stair, op, GROUND, largeur_mur, 0, hauteur_mur);
		}
		if(g){
			this.drawWAll(color_ground, op, GROUND, 0, -largeur_mur, 0);
			this.drawWAll(color_stair, op, GROUND, 0, -largeur_mur, hauteur_mur);
		}
		if(d){
			this.drawWAll(color_ground, op, GROUND, 0, largeur_mur, 0);
			this.drawWAll(color_stair, op, GROUND, 0, largeur_mur, hauteur_mur);
		}
		if(d1){
			this.drawWAll(color_ground, op, GROUND, largeur_mur, largeur_mur, 0);
			this.drawWAll(color_stair, op, GROUND, largeur_mur, largeur_mur, hauteur_mur);
		}
		if(g1){
			this.drawWAll(color_ground, op, GROUND, largeur_mur, -largeur_mur, 0);
			this.drawWAll(color_stair, op, GROUND, largeur_mur, -largeur_mur, hauteur_mur);
		}
		
		if(!h){
			this.drawWAll(color_wall, op, WALL_HAUT, largeur_mur, 0, 0);
			if(g) this.drawWAll(color_wall,op, WALL_HAUT, largeur_mur, -largeur_mur, 0);
			if(d) this.drawWAll(color_wall,op, WALL_HAUT, largeur_mur, largeur_mur, 0);
		}else{
			if(!h1) this.drawWAll(color_wall_dark,op, WALL_HAUT, largeur_mur*2, 0, 0);
			if(!g1) this.drawWAll(color_wall_dark,op, WALL_GAUCHE, largeur_mur, 0, 0); else this.drawWAll(color_wall_dark,op, WALL_HAUT, largeur_mur*2, -largeur_mur, 0);
			if(!d1) this.drawWAll(color_wall_dark,op, WALL_DROITE, largeur_mur, 0, 0); else this.drawWAll(color_wall_dark,op, WALL_HAUT, largeur_mur*2, largeur_mur, 0);
		}
		
		if(!g) {
			if(h && g1) this.drawWAll(color_wall, op, WALL_HAUT, largeur_mur, -largeur_mur, 0);
			this.drawWAll(color_wall,op, WALL_GAUCHE, 0, 0, 0);
		}else if(!g1) this.drawWAll(color_wall, op, WALL_HAUT, largeur_mur, -largeur_mur, 0);
		if(!d) {
			if(h && d1)this.drawWAll(color_wall, op, WALL_HAUT, largeur_mur, largeur_mur, 0);
			this.drawWAll(color_wall,op, WALL_DROITE, 0, 0, 0);
		}else if(!d1) this.drawWAll(color_wall, op, WALL_HAUT, largeur_mur, largeur_mur, 0);
		

		
	}
	

	private void drawWAll(Color c,IDrawOperation op,Point3D[] wall,int dist,int dx,int dy){
		
		Point3D a = new Point3D(0,0,0);
		Point3D b = new Point3D(0,0,0);
		int[] xs = new  int[4];
		int[] ys = new  int[4];
		
		
		for(int i=0;i<4;i++){
			int j = (i == 3) ? 0 :i+1;

			a.x = wall[i].x + dx;
			a.y = wall[i].y + dy;
			a.z = wall[i].z;
			b.x = wall[j].x + dx;
			b.y = wall[j].y + dy;
			b.z = wall[j].z;

			checkPoint(a, dist);
			checkPoint(b, dist);
			
			xs[i] = sx+a.x;
			ys[i] = sy-a.y;
		}
		
		op.fillPolygone(c, xs, ys, 1.0f);
		op.drawPolygone(Color.black, xs, ys, 1.0f);
	}
	
	
	private void checkPoint(Point3D p,int dist){
		double limx = 120;
		double limy = 40;
		double sx = 1.0;
		double sy = 1.0;
		
		double z = Math.sqrt(p.z+ dist)*8;
	
		
		double x = p.x * (1-1 / limx * z);
		double y = (p.y-limy) / p.x * x + limy;

		p.x = (int) (x * sx);
		p.y = (int) (y * sy);
	}

}

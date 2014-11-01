package com.renaud.laby.player;

import java.awt.Color;

import com.renaud.laby.Direction;
import com.renaud.laby.Labyrinthe;
import com.renaud.laby.LabyrintheTools;
import com.renaud.laby.view.IDrawOperation;




public class RenderWall3D {
	
	
	
	
	public class Point3D{
		int x,y,z;

		public Point3D(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	private final static int largeur_mur = 50;
	private final static int longueur_mur = 100;
	private final static int hauteur_mur = 100;
	
		
	private double vx = 0;
	private double vy = 50;
	private double vz = 450;
	private int profondeurVue = 5;
	private int sx = 250;
	private int sy = 110;

	protected Labyrinthe laby;
	protected Player player;
	
	private Point3D[] WALL_HAUT = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,0,0)};
	private Point3D[] WALL_GAUCHE = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,hauteur_mur,0),new Point3D(-largeur_mur/2,hauteur_mur,longueur_mur),new Point3D(-largeur_mur/2,0,longueur_mur)};
	private Point3D[] WALL_DROITE = {new Point3D(largeur_mur/2,0,0),new Point3D(largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,hauteur_mur,longueur_mur),new Point3D(largeur_mur/2,0,longueur_mur)};
	private Point3D[] GROUND = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,0,longueur_mur),new Point3D(largeur_mur/2,0,longueur_mur),new Point3D(largeur_mur/2,0,0)};
	
	
	
	
	
	public RenderWall3D(Labyrinthe laby, Player player) {
		this.laby = laby;
		this.player = player;
	}


	public void render(IDrawOperation op) {
		
		Color back_wall = Color.darkGray;
		Color wall = Color.lightGray;
		Color sol = Color.green;
		for(int i=profondeurVue-1;i>=0;i--){
			int pos = LabyrintheTools.nextPos(laby, this.player.getDirectionRegard(), this.player.getPosition(), i);//nextPos(i);
			
			if(pos != 0){
				int kind = this.kind(pos, player.getDirectionRegard());
				
				boolean g = (kind & Direction.GAUCHE_DIR) == Direction.GAUCHE_DIR;
				boolean d = (kind & Direction.DROITE_DIR) == Direction.DROITE_DIR;
				boolean h = (kind & Direction.HAUT_DIR) == Direction.HAUT_DIR;
			
				this.drawWAll(sol, op, GROUND, 0, 0, longueur_mur * i);
				this.drawWAll(Color.gray, op, GROUND, 0, hauteur_mur, longueur_mur * i);
				
				if(!h)this.drawWAll(back_wall, op, WALL_HAUT, 0, 0, longueur_mur * (i+1));
				if(!d){
					this.drawWAll(wall, op, WALL_DROITE, 0, 0, longueur_mur * i);
					this.drawWAll(back_wall, op, WALL_HAUT, largeur_mur, 0, longueur_mur * i);
				}else {
					this.drawWAll(back_wall, op, WALL_HAUT, largeur_mur, 0, longueur_mur * (i+1));
					this.drawWAll(Color.gray, op, GROUND, largeur_mur, hauteur_mur, longueur_mur * i);
					this.drawWAll(sol, op, GROUND, largeur_mur, 0, longueur_mur * i);
				}
				if(!g) {
					this.drawWAll(wall, op, WALL_GAUCHE, 0, 0, longueur_mur * i); 
					this.drawWAll(back_wall, op, WALL_HAUT, -largeur_mur, 0, longueur_mur * i);
				}else {
					this.drawWAll(back_wall, op, WALL_HAUT, -largeur_mur, 0, longueur_mur * (i+1));
					this.drawWAll(Color.gray, op, GROUND, -largeur_mur, hauteur_mur, longueur_mur * i);
					this.drawWAll(sol, op, GROUND, -largeur_mur, 0, longueur_mur * i);
				}
			
			}
			
		}
	}
	
//	protected int nextPos(int i){
//		int pos = 0;
//		int h = player.getPosition()/laby.getLargeurTable();
//		int l = player.getPosition()%laby.getLargeurTable();
//		
//		switch (player.getDirectionRegard()) {
//			case Direction.NORD: pos = ((h-i)>0)?player.getPosition()- this.laby.getLargeurTable()*i : -1; break;
//			case Direction.SUD: pos = ((h+i)<(laby.getHauteurTable()-1))?player.getPosition()+this.laby.getLargeurTable()*i : -1; break;
//			case Direction.EST: pos = ((l+i)<(laby.getLargeurTable()-1))?player.getPosition()+i : -1; break;
//			case Direction.OUEST: pos = ((l-i)>0)?player.getPosition()-i : -1; break;
//		}
//		
//		return pos;
//	}
	

	
	protected int kind(int position,int directionRegard){
		if(position >= 0){
			int kind = 0;
			int[] tab = laby.getTable();
			int lar = laby.getLargeurTable();
	
			switch (directionRegard) {
				case Direction.SUD:
					if (tab[position - 1] == 0)
						kind += Direction.DROITE_DIR;
					if (tab[position + 1] == 0)
						kind += Direction.GAUCHE_DIR;
					if (tab[position + lar] == 0) 
						kind += Direction.HAUT_DIR;
					break;
	
				case Direction.NORD:
					if (tab[position + 1] == 0)
						kind += Direction.DROITE_DIR;
					if (tab[position - 1] == 0)
						kind += Direction.GAUCHE_DIR;
					if (tab[position - lar] == 0)
						kind += Direction.HAUT_DIR;
						
					break;
	
				case Direction.OUEST:
					if (tab[position - lar] == 0)
						kind += Direction.DROITE_DIR;
					if (tab[position + lar] == 0)
						kind += Direction.GAUCHE_DIR;
					if (tab[position - 1] == 0)
						kind += Direction.HAUT_DIR;
						
					break;
	
				case Direction.EST:
					if (tab[position + lar] == 0)
						kind += Direction.DROITE_DIR;
					if (tab[position - lar] == 0)
						kind += Direction.GAUCHE_DIR;
					if (tab[position + 1] == 0) 
						kind += Direction.HAUT_DIR;
						
					break;
			}
			return kind;
		} else return 0;
		
	}
	
	protected void drawWAll(Color c,IDrawOperation op,Point3D[] wall, int dx, int dy, int dz){
		
		Point3D a = new Point3D(0,0,0);
		Point3D b = new Point3D(0,0,0);
		int[] xs = new  int[4];
		int[] ys = new  int[4];
		
		for(int i=0;i<4;i++){
			int j = (i == 3) ? 0 :i+1;

			a.x = wall[i].x + dx;
			a.y = wall[i].y + dy;
			a.z = wall[i].z + dz;
			b.x = wall[j].x + dx;
			b.y = wall[j].y + dy;
			b.z = wall[j].z + dz;

			checkPoint(a);
			checkPoint(b);
			
			xs[i] = sx+a.x;
			ys[i] = sy-a.y;
		}
		
		op.fillPolygone(c, xs, ys, 1.0f);
		op.drawPolygone(Color.black, xs, ys, 1.0f);
	}

	


	
	
	private void checkPoint(Point3D p){
		
		
		
		double a = (vy-p.y) / (vx-p.x);
	
		double z = Math.min(vz, p.z);
		

		double var = z / vz;
		double yy = 1 - 1/(1+var*1.5);
		double d = yy * (vx-p.x);
		
		p.x += (int)d;
		p.y += (int)(a * d);
	}
}

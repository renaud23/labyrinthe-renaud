package com.renaud.laby.player;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.SimpleImageLoader;

public class RenderWall3D {
	
	
	private static BufferedImage image; 
	
	static{
		SimpleImageLoader smp = new SimpleImageLoader();
		try {
			image = ImageIO.read(new File("C:/Users/Renaud/git/labyrinthe-renaud/laby/image/wall.jpg") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};//smp.getImage("C:/Users/Renaud/git/labyrinthe-renaud/laby/image/wall.jpg");
	}
	
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
	
	
	
	
	private int sx = 250;
	private int sy = 150;

	private Labyrinthe laby;
	private Player player;
	
	private Point3D[] WALL_HAUT = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,0,0)};
	private Point3D[] WALL_GAUCHE = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,hauteur_mur,0),new Point3D(-largeur_mur/2,hauteur_mur,longueur_mur),new Point3D(-largeur_mur/2,0,longueur_mur)};
	private Point3D[] WALL_DROITE = {new Point3D(largeur_mur/2,0,0),new Point3D(largeur_mur/2,hauteur_mur,0),new Point3D(largeur_mur/2,hauteur_mur,longueur_mur),new Point3D(largeur_mur/2,0,longueur_mur)};
	private Point3D[] GROUND = {new Point3D(-largeur_mur/2,0,0),new Point3D(-largeur_mur/2,0,longueur_mur),new Point3D(largeur_mur/2,0,longueur_mur),new Point3D(largeur_mur/2,0,0)};
	
	
	
	
	
	public RenderWall3D(Labyrinthe laby, Player player) {
		this.laby = laby;
		this.player = player;
	}


	public void render(IDrawOperation op) {
		int dist = 5;
		Color back_wall = Color.darkGray;
		Color wall = Color.lightGray;
		Color sol = Color.green;
		for(int i=dist-1;i>=0;i--){
			int pos = nextPos(i);
			
			if(pos != 0){
				int kind = this.kind(pos, player.getDirectionRegard());
				
				boolean g = (kind & Player.GAUCHE_DIR) == Player.GAUCHE_DIR;
				boolean d = (kind & Player.DROITE_DIR) == Player.DROITE_DIR;
				boolean h = (kind & Player.HAUT_DIR) == Player.HAUT_DIR;
			
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
		
		
//		drawWAll(Color.green, op, GROUND, 0, 0, longueur_mur*3);
//		drawWAll(Color.green, op, GROUND, 0, 0, longueur_mur*2);
//		drawWAll(Color.green, op, GROUND, 0, 0, longueur_mur);
//		drawWAll(Color.green, op, GROUND, 0, 0, 0);
//		
//		drawWAll(Color.red, op, WALL_GAUCHE, 0, 0, longueur_mur*3);
//		drawWAll(Color.red, op, WALL_DROITE, 0, 0, longueur_mur*3);
//		
//		drawWAll(Color.red, op, WALL_GAUCHE, 0, 0, longueur_mur*2);
//		drawWAll(Color.red, op, WALL_DROITE, 0, 0, longueur_mur*2);
//		
//		drawWAll(Color.red, op, WALL_GAUCHE, 0, 0, longueur_mur);
//		drawWAll(Color.red, op, WALL_DROITE, 0, 0, longueur_mur);
//		
//		drawWAll(Color.red, op, WALL_GAUCHE, 0, 0, 0);
//		drawWAll(Color.red, op, WALL_DROITE, 0, 0, 0);
		
		
		
		
	}
	
	private int nextPos(int i){
		int pos = 0;
		int h = player.getPosition()/laby.getLargeurTable();
		int l = player.getPosition()%laby.getLargeurTable();
		
		switch (player.getDirectionRegard()) {
			case Player.NORD: pos = ((h-i)>0)?player.getPosition()- this.laby.getLargeurTable()*i : 0; break;
			case Player.SUD: pos = ((h+i)<(laby.getHauteurTable()-1))?player.getPosition()+this.laby.getLargeurTable()*i : 0; break;
			case Player.EST: pos = ((l+i)<(laby.getLargeurTable()-1))?player.getPosition()+i : 0; break;
			case Player.OUEST: pos = ((l-i)>0)?player.getPosition()-i : 0; break;
		}
		
		return pos;
	}
	

	
	public int kind(int position,int directionRegard){
		int kind = 0;
		int[] tab = laby.getTable();
		int lar = laby.getLargeurTable();

		switch (directionRegard) {
			case Player.SUD:
				if (tab[position - 1] == 0)
					kind += Player.DROITE_DIR;
				if (tab[position + 1] == 0)
					kind += Player.GAUCHE_DIR;
				if (tab[position + lar] == 0) 
					kind += Player.HAUT_DIR;
				break;

			case Player.NORD:
				if (tab[position + 1] == 0)
					kind += Player.DROITE_DIR;
				if (tab[position - 1] == 0)
					kind += Player.GAUCHE_DIR;
				if (tab[position - lar] == 0)
					kind += Player.HAUT_DIR;
					
				break;

			case Player.OUEST:
				if (tab[position - lar] == 0)
					kind += Player.DROITE_DIR;
				if (tab[position + lar] == 0)
					kind += Player.GAUCHE_DIR;
				if (tab[position - 1] == 0)
					kind += Player.HAUT_DIR;
					
				break;

			case Player.EST:
				if (tab[position + lar] == 0)
					kind += Player.DROITE_DIR;
				if (tab[position - lar] == 0)
					kind += Player.GAUCHE_DIR;
				if (tab[position + 1] == 0) 
					kind += Player.HAUT_DIR;
					
				break;
		}
		return kind;
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
//		op.drawPolygone( xs, ys, image);
	}

	
	private final static double limz = 150;
	private final static double limy = 60;

	
//	private void checkPoint(Point3D p,int dist){
//		
//		double sx = 1.0;
//		double sy = 1.0;
//		
//		double z = Math.sqrt(p.z+ dist)*8;
//		
//		double x = p.x * (1-1 / limz * z);
//		double y = (p.y-limy) / p.x * x + limy;
//	
//		p.x = (int) (x * sx);
//		p.y = (int) (y * sy);
//		
//		
//	}
	
	private void checkPoint(Point3D p){
		
		double cos = Math.cos(Math.PI/16); 
		double sin = Math.sin(Math.PI/16); 
		
//		p.x = (int) (p.x * cos + p.y * sin);
//		p.y = (int) (-p.x*sin+p.y*cos);

//		p.y = (int) (p.y*cos+p.z*sin);
//		p.z = (int) (-p.y*sin+p.z*cos);
		
//		p.x = (int) (p.x*cos-p.z*sin);
//		p.z = (int) (p.x*sin+p.z*cos);
		
		double vx = 0;
		double vy = 50;
		double vz = 550;
		
//		vy = (int) (vy*cos+vz*sin);
//		vz = (int) (-vy*sin+vz*cos);

//		double a = (p.y-vy) / (p.x-vx);
		double a = (vy-p.y) / (vx-p.x);
	
		double z = Math.min(vz, p.z);//Math.sqrt(p.z)*8);
		

		double var = z / vz;
		double yy = 1 - 1/(1+var*1.5);
		double d = yy * (vx-p.x);
		
		p.x += (int)d;
		p.y += (int)(a * d);
		
//		double cos = Math.cos(Math.PI/8); 
//		double sin = Math.sin(Math.PI/8); 
//		p.x = (int) (p.x*cos+p.y*sin);
//		p.y = (int) (-p.x*sin+p.y*cos);
	}
}

package com.renaud.laby;

public class LabyrintheTools {
	
	public final static int BLOCKED = -1;
	
	public static int nextPos(Labyrinthe laby, int direction,int position, int i){
		int pos = 0;
		int h = position/laby.getLargeurTable();
		int l = position%laby.getLargeurTable();
		
		switch (direction) {
			case Direction.NORD: pos = ((h-i)>0)?position- laby.getLargeurTable()*i : BLOCKED; break;
			case Direction.SUD: pos = ((h+i)<(laby.getHauteurTable()-1))?position+laby.getLargeurTable()*i : BLOCKED; break;
			case Direction.EST: pos = ((l+i)<(laby.getLargeurTable()-1))?position+i : BLOCKED; break;
			case Direction.OUEST: pos = ((l-i)>0)?position-i : BLOCKED; break;
		}
		
		return pos;
	}
	
	
	public static int getDirection(int position,int cible, int labyLargeur){
		int dir = 0;
		
		int hp = (position / labyLargeur);
		int hc = (cible / labyLargeur);
		int lp = (position % labyLargeur);
		int lc = (cible % labyLargeur);
		
		if(hp != hc){
			if(hp>hc) dir += Direction.NORD;
			else dir += Direction.SUD;
		}
		if(lp != lc){
			if(lp>lc) dir += Direction.OUEST;
			else dir += Direction.EST;
		}
		
		return dir;
	}
	
	
	public static int getVariation(int position,int cible, int labyLargeur){
		int variation = 0;
		int hp = (position / labyLargeur);
		int hc = (cible / labyLargeur);
		int lp = (position % labyLargeur);
		int lc = (cible % labyLargeur);
		
		if(hp != hc){
			if(hp>hc) variation += -labyLargeur;
			else variation += labyLargeur;
		}
		if(lp != lc){
			if(lp>lc) variation += -1;
			else variation += 1;
		}
		return variation;
	}
	
	public static int getLeftDirection(int direction){
		int d = direction;
		d *= 2;
		if(d > Direction.EST) d=Direction.NORD;
		
		return d;
	}
	
	public static int getRightDirection(int direction){
		int d = direction;
		d /= 2;
		if(d < Direction.NORD) d=Direction.EST;
		
		return d;
	}
	
	public static int getBackDirection(int direction){
		int d = getRightDirection(direction);
		
		return getRightDirection(d);
	}
}

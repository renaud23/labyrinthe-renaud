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
}

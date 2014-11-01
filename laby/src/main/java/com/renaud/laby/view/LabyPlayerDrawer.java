package com.renaud.laby.view;

import java.awt.Color;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.player.Player;

public class LabyPlayerDrawer extends LabyDrawer{
	
	private Player player;

	public LabyPlayerDrawer(Player player,Labyrinthe laby) {
		super(laby);
		
		this.player = player;
	}
	
	public LabyPlayerDrawer(Player player,Labyrinthe laby, int x, int y) {
		super(laby,x,y);
		this.laby = laby;
		this.x = x;
		this.y = y;
		
		this.player = player;
	}
	
	public LabyPlayerDrawer(Player player,Labyrinthe laby, int x, int y,int dl,int dh) {
		super(laby,x,y,dl,dh);
		this.laby = laby;
		this.x = x;
		this.y = y;
		
		this.player = player;
	}

	@Override
	public void draw() {
		super.draw();
		int yi = player.getPosition() / laby.getLargeurTable();
		int xi = player.getPosition() % laby.getLargeurTable();
		
		drawOperation.fillRect(Color.green, x + xi * dalleLargeur, y + yi * dalleHauteur, dalleLargeur, dalleHauteur, 1.0f);
		
	}
	
	
	

}

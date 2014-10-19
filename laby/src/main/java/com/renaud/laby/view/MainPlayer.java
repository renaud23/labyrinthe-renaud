package com.renaud.laby.view;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.LabyKeyListener;
import com.renaud.laby.game.Game;
import com.renaud.laby.player.Player;

public class MainPlayer {

	public static void main(String[] args) {
		Fenetre f = new Fenetre(400, 400);

		Labyrinthe l = new Labyrinthe(10, 10);
		l.genere();

		// game
		Game g = new Game();
		f.setGame(g);
		 
		
		
		Player p = new Player(l);
		g.addActivable(p);
		f.addDrawable(p);
		f.addDrawable(new LabyDrawer(l,0,200));
		
		
		f.getFrame().addKeyListener(new LabyKeyListener(g));
		
		g.start();
		
		
		
		
	}

}

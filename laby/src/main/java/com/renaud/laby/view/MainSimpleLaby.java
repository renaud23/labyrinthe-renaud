package com.renaud.laby.view;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.LabyKeyListener;
import com.renaud.laby.game.Game;
import com.renaud.laby.worm.Worm;

public class MainSimpleLaby {

	public static void main(String[] args) {
		Fenetre f = new Fenetre(400, 400);

		Labyrinthe l = new Labyrinthe(10, 10);
		l.genere();

		// game
		Game g = new Game();

		f.setGame(g);

		// draw
		f.addDrawable(new LabyDrawer(l));

		for (int i = 0; i < 2; i++) {
			Worm w = new Worm(l);
			g.addActivable(w);
			f.addDrawable(w);
		}
		
		
		// controller
//		LabyKeyListener lk = new LabyKeyListener(g);
//		f.getFrame().addKeyListener(lk);
		
		g.start();
	}

}

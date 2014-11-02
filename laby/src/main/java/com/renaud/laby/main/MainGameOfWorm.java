package com.renaud.laby.main;

import java.util.ArrayList;
import java.util.List;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.LabyKeyListener;
import com.renaud.laby.controller.LabyMouseListener;
import com.renaud.laby.game.GameOfWorm;
import com.renaud.laby.player.Player;
import com.renaud.laby.view.Fenetre;
import com.renaud.laby.worm.Worm;
import com.renaud.laby.worm.comportement.Suiveur;

public class MainGameOfWorm {

	public static void main(String[] args) {
		Fenetre f = new Fenetre(400, 400);

		Labyrinthe l = new Labyrinthe(15, 15);
		l.genere();
		Player p = new Player(l);

		List<Worm> ws = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Worm w = new Worm(l, 5, 50);
			w.setComportement(new Suiveur(l, p, w, 200));
			ws.add(w);
		}
		
		

		GameOfWorm gow = new GameOfWorm(p, ws, l);

		f.setGame(gow);
		f.addDrawable(gow);

		f.getFrame().addKeyListener(new LabyKeyListener(gow));
		f.getBuffer().addMouseMotionListener(new LabyMouseListener(gow));
		
		gow.start();

	}
}

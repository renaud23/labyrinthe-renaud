package com.renaud.laby.main;

import java.util.ArrayList;
import java.util.List;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.LabyKeyListener;
import com.renaud.laby.game.GameOfWorm;
import com.renaud.laby.player.Player;
import com.renaud.laby.view.Fenetre;
import com.renaud.laby.worm.Worm;

public class MainGameOfWorm {

	public static void main(String[] args) {
		Fenetre f = new Fenetre(400, 400);

		Labyrinthe l = new Labyrinthe(15, 15);
		l.genere();
		Player p = new Player(l);
		Worm w = new Worm(l, 5, 1000);

		List<Worm> ws = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			ws.add(new Worm(l, 5, 50));
		}

		GameOfWorm gow = new GameOfWorm(p, ws, l);

		f.setGame(gow);
		f.addDrawable(gow);

		f.getFrame().addKeyListener(new LabyKeyListener(gow));

		gow.start();

	}
}

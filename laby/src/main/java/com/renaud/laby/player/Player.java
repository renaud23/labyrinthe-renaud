package com.renaud.laby.player;

import java.awt.Color;
import java.util.Random;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.IController;
import com.renaud.laby.game.IActivate;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;

public class Player implements IController, IActivate, IDrawable, DrawOperationAware {

	public static final int GAUCHE_DIR = 2;
	public static final int DROITE_DIR = 1;
	public static final int HAUT_DIR = 8;
	public static final int BAS_DIR = 4;

	public static final int NORD = 1;
	public static final int SUD = 4;
	public static final int EST = 8;
	public static final int OUEST = 2;

	private IDrawOperation op;

	private RenderWallASCII renderASCII;
	
	private RenderWall3D render3D;

	private Labyrinthe laby;
	private int position = 0;
	private int directionRegard;
	
	private int mousex;
	private int mousey;

	private Random r = new Random();

	private int[] memory;

	private static final int PLEIN = 1;
	private static final int VIDE = -1;

	public Player(Labyrinthe laby) {
		this.laby = laby;

		int t[] = laby.getTable();

		while (t[position] == 1) {
			position = r.nextInt(t.length);
		}

		directionRegard = NORD;

		this.memory = new int[laby.getLargeurTable() * laby.getHauteurTable()];
		this.memory[position] = VIDE;
		
		this.render3D = new RenderWall3D(laby, this);
		this.renderASCII = new RenderWallASCII(laby, this);
	}

	@Override
	public void activate() {
		this.look();
	}

	// Controller

	@Override
	public void up() {
		this.move(HAUT_DIR);
	}

	@Override
	public void left() {
		// this.move(GAUCHE_DIR);
		directionRegard = directionRegard << 1;
		if (directionRegard > EST)
			directionRegard = NORD;
	}

	@Override
	public void down() {
		this.move(BAS_DIR);
	}

	@Override
	public void right() {
		// this.move(DROITE_DIR);
		directionRegard = directionRegard >> 1;
		if (directionRegard == 0)
			directionRegard = EST;
	}

	@Override
	public void turnRight() {
		directionRegard = directionRegard >> 1;
		if (directionRegard == 0)
			directionRegard = EST;
	}

	@Override
	public void turnLeft() {
		directionRegard = directionRegard << 1;
		if (directionRegard > EST)
			directionRegard = NORD;
	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;
	}

	@Override
	public boolean isChange() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setChange() {
		// TODO Auto-generated method stub

	}

	private void look() {
		int[] tab = laby.getTable();
		int lar = laby.getLargeurTable();
		int haut = 0;
		int left = 0;
		switch (directionRegard) {
			case NORD:
				haut = -lar;
				left = -1;
				break;
			case SUD:
				haut = lar;
				left = 1;
				break;
			case OUEST:
				haut = -1;
				left = -lar;
				break;
			case EST:
				haut = 1;
				left = lar;
				break;
		}
		if (tab[position + haut] == PLEIN)
			memory[position + haut] = PLEIN;
		else {
			memory[position + haut] = VIDE;
			if (tab[position + 2 * haut] == 1)
				memory[position + 2 * haut] = PLEIN;
			else
				memory[position + 2 * haut] = VIDE;
		}
		if (tab[position + left] == PLEIN)
			memory[position + left] = PLEIN;
		else
			memory[position + left] = VIDE;
		if (tab[position - left] == PLEIN)
			memory[position - left] = PLEIN;
		else
			memory[position - left] = VIDE;
	}

	@Override
	public void draw() {
		int lar = laby.getLargeurTable();


		this.renderASCII.render(op);
		this.render3D.render(op);

		if (directionRegard == NORD)
			op.drawChar("NORD", 10, 150);
		if (directionRegard == SUD)
			op.drawChar("SUD", 10, 150);
		if (directionRegard == EST)
			op.drawChar("EST", 10, 150);
		if (directionRegard == OUEST)
			op.drawChar("OUEST", 10, 150);
		op.drawChar("Pos " + position, 10, 190);

		for (int i = 0; i < laby.getHauteurTable(); i++) {
			for (int j = 0; j < laby.getLargeurTable(); j++) {
				if (memory[i * lar + j] == 1)
					this.op.fillRect(Color.black, 0 + j * 5, 200 + i * 5, 5, 5, 1.0f);
				else
					if ((i * lar + j) == position)
						this.op.fillRect(Color.green, 0 + j * 5, 200 + i * 5, 5, 5, 1.0f);
					else
						if (memory[i * lar + j] == -1)
							this.op.fillRect(Color.yellow, 0 + j * 5, 200 + i * 5, 5, 5, 1.0f);
						else
							this.op.fillRect(Color.gray, 0 + j * 5, 200 + i * 5, 5, 5, 1.0f);
			}
		}

	}
	
	

	private boolean move(int code) {
		int[] tab = laby.getTable();
		int lar = laby.getLargeurTable();
		int posInit = position;
		switch (code) {
			case GAUCHE_DIR:
				if (directionRegard == NORD && tab[position - 1] == 0)
					position -= 1;
				if (directionRegard == SUD && tab[position + 1] == 0)
					position += 1;
				if (directionRegard == EST && tab[position - lar] == 0)
					position -= lar;
				if (directionRegard == OUEST && tab[position + lar] == 0)
					position += lar;
				break;

			case DROITE_DIR:
				if (directionRegard == NORD && tab[position + 1] == 0)
					position += 1;
				if (directionRegard == SUD && tab[position - 1] == 0)
					position -= 1;
				if (directionRegard == EST && tab[position + lar] == 0)
					position += lar;
				if (directionRegard == OUEST && tab[position - lar] == 0)
					position -= lar;
				break;

			case HAUT_DIR:
				if (directionRegard == NORD && tab[position - lar] == 0)
					position -= lar;
				if (directionRegard == SUD && tab[position + lar] == 0)
					position += lar;
				if (directionRegard == EST && tab[position + 1] == 0)
					position += 1;
				if (directionRegard == OUEST && tab[position - 1] == 0)
					position -= 1;
				break;

			case BAS_DIR:
				if (directionRegard == NORD && tab[position + lar] == 0)
					position += lar;
				if (directionRegard == SUD && tab[position - lar] == 0)
					position -= lar;
				if (directionRegard == EST && tab[position - 1] == 0)
					position -= 1;
				if (directionRegard == OUEST && tab[position + 1] == 0)
					position += 1;
				break;
		}

		if (position != posInit)
			return true;
		else
			return false;
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getPosition() {
		return position;
	}

	public int getDirectionRegard() {
		return directionRegard;
	}

	@Override
	public void mouseMoved(int x, int y) {
		
		
	}

}

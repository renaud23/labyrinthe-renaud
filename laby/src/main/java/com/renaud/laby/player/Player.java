package com.renaud.laby.player;

import java.util.Random;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.IController;
import com.renaud.laby.game.IActivate;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;

public class Player implements IController,IActivate, IDrawable, DrawOperationAware{
	
	private final int GAUCHE_DIR = 2;
	private final int DROITE_DIR = 1;
	private final int DEVANT_DIR = 8;
	private final int DERRIERE_DIR = 4;
	
	private final int NORD = 1;
	private final int SUD = 4;
	private final int EST = 8;
	private final int OUEST = 2;

	private IDrawOperation op;
	
	private RenderWall rw = new RenderWall();
	
	private Labyrinthe laby;
	private int pos = 0;
	private int dirVue;
	
	private Random r = new Random();
	
	
	
	
	
	
	
	
	
	
	
	public Player(Labyrinthe laby) {
		this.laby = laby;
		
		int t[] = laby.getTable();
		
		while(t[pos] == 1){
			pos = r.nextInt(t.length);
		}
		
		dirVue = NORD;
//		if(laby.getTable()[pos-1] == 0 && laby.getTable()[pos+1] == 0) dirVue = EST;
	}


	@Override
	public void activate() {


		
	}
	
	
	// Controller
	
	@Override
	public void up() {
		this.move(DEVANT_DIR);
	}

	@Override
	public void left() {
		this.move(GAUCHE_DIR);
	}

	@Override
	public void down() {
		this.move(DERRIERE_DIR);
	}

	@Override
	public void right() {
		this.move(DROITE_DIR);
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

	@Override
	public void draw() {
		int kind = 0;
		int[] tab = laby.getTable();
		int lar = laby.getLargeurTable();
		
		switch(dirVue){
			case SUD:
				if(tab[pos-1] == 0) kind += DROITE_DIR;
				if(tab[pos+1] == 0) kind += GAUCHE_DIR;
				if(tab[pos+lar] == 0) kind += DEVANT_DIR;
				break;
				
			case NORD:
				if(tab[pos+1] == 0) kind += DROITE_DIR;
				if(tab[pos-1] == 0) kind += GAUCHE_DIR;
				if(tab[pos-lar] == 0) kind += DEVANT_DIR;
				break;
				
			case OUEST:
				if(tab[pos-lar] == 0) kind += DROITE_DIR;
				if(tab[pos+lar] == 0) kind += GAUCHE_DIR;
				if(tab[pos-1] == 0) kind += DEVANT_DIR;
				break;
				
			case EST:
				if(tab[pos+lar] == 0) kind += DROITE_DIR;
				if(tab[pos-lar] == 0) kind += GAUCHE_DIR;
				if(tab[pos+1] == 0) kind += DEVANT_DIR;
				break;
		}
		
		
		this.rw.render(op, kind);
		
		if(dirVue == NORD) op.drawChar("NORD", 10, 150);
		if(dirVue == SUD) op.drawChar("SUD", 10, 150);
		if(dirVue == EST) op.drawChar("EST", 10, 150);
		if(dirVue == OUEST) op.drawChar("OUEST", 10, 150);
	}
	
	private void move(int code){
		int[] tab = laby.getTable();
		int l = laby.getLargeurTable();
		switch(code){
			case GAUCHE_DIR:
				break;
			case DROITE_DIR:
				break;
			case DEVANT_DIR:
				if(dirVue == NORD && tab[pos-l] == 0) pos -= l;
				if(dirVue == SUD && tab[pos+l] == 0) pos += l;
				if(dirVue == EST && tab[pos+1] == 0) pos += 1;
				if(dirVue == OUEST && tab[pos-1] == 0) pos -= l;
				break;
			case DERRIERE_DIR:
				break;
		}
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


	@Override
	public void turnRight() {
		dirVue = dirVue>>1;
		if(dirVue == 0)dirVue=EST;
	}


	@Override
	public void turnLeft() {
		dirVue = dirVue<<1;
		if(dirVue > EST)dirVue=NORD;
	}

}

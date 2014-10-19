package com.renaud.laby.player;

import java.awt.Color;
import java.util.Random;

import com.renaud.laby.Labyrinthe;
import com.renaud.laby.controller.IController;
import com.renaud.laby.game.IActivate;
import com.renaud.laby.view.DrawOperationAware;
import com.renaud.laby.view.IDrawOperation;
import com.renaud.laby.view.IDrawable;
import com.renaud.laby.view.LabyDrawer;

public class Player implements IController,IActivate, IDrawable, DrawOperationAware{
	
	private final int GAUCHE_DIR = 2;
	private final int DROITE_DIR = 1;
	private final int HAUT_DIR = 8;
	private final int BAS_DIR = 4;
	
	private final int GAUCHE_DIR1 = 32;
	private final int DROITE_DIR1 = 16;
	private final int HAUT_DIR1 = 128;
	private final int BAS_DIR1 = 64;
	
	private final int NORD = 1;
	private final int SUD = 4;
	private final int EST = 8;
	private final int OUEST = 2;

	private IDrawOperation op;
	
	private RenderWall2 rw = new RenderWall2();
	
	private Labyrinthe laby;
	private int pos = 0;
	private int dirVue;
	
	private Random r = new Random();
	
	private int[] memory;
	
	
	
	
	private static final int PLEIN = 1;
	private static final int VIDE = -1;
	
	
	
	
	public Player(Labyrinthe laby) {
		this.laby = laby;
		
		int t[] = laby.getTable();
		
		while(t[pos] == 1){
			pos = r.nextInt(t.length);
		}
		
		dirVue = NORD;

		this.memory = new int[laby.getLargeurTable() * laby.getHauteurTable()];
		this.memory[pos] = VIDE;
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
//		this.move(GAUCHE_DIR);
		dirVue = dirVue<<1;
		if(dirVue > EST)dirVue=NORD;
	}

	@Override
	public void down() {
		this.move(BAS_DIR);
	}

	@Override
	public void right() {
//		this.move(DROITE_DIR);
		dirVue = dirVue>>1;
		if(dirVue == 0)dirVue=EST;
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

	private void look(){
		int[] tab = laby.getTable();
		int lar = laby.getLargeurTable();
		int haut = 0;
		int left = 0;
		switch(dirVue){
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
		if(tab[pos+haut] == PLEIN) memory[pos+haut] = PLEIN; 
		else{ 
			memory[pos+haut] = VIDE;
			if(tab[pos+2*haut] == 1) memory[pos+2*haut] = PLEIN; else memory[pos+2*haut] = VIDE;
		}
		if(tab[pos+left] == PLEIN) memory[pos+left] = PLEIN; else memory[pos+left] = VIDE;
		if(tab[pos-left] == PLEIN) memory[pos-left] = PLEIN; else memory[pos-left] = VIDE;
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
				if(tab[pos+lar] == 0){
					kind += HAUT_DIR;
					int p = pos+lar;
					if(tab[p+lar] == 0) kind += HAUT_DIR1;
					if(tab[p-1] == 0)kind += DROITE_DIR1;
					if(tab[p+1] == 0)kind += GAUCHE_DIR1;
				}
				break;
				
			case NORD:
				if(tab[pos+1] == 0) kind += DROITE_DIR;
				if(tab[pos-1] == 0) kind += GAUCHE_DIR;
				if(tab[pos-lar] == 0){
					kind += HAUT_DIR;
					int p = pos-lar;
					if(tab[p-lar] == 0) kind += HAUT_DIR1;
					if(tab[p-1] == 0)kind += GAUCHE_DIR1;
					if(tab[p+1] == 0)kind += DROITE_DIR1;
				}
				break;
				
			case OUEST:
				if(tab[pos-lar] == 0) kind += DROITE_DIR;
				if(tab[pos+lar] == 0) kind += GAUCHE_DIR;
				if(tab[pos-1] == 0){
					kind += HAUT_DIR;
					int p = pos-1;
					if(tab[p-lar] == 0) kind += DROITE_DIR1;
					if(tab[p+lar] == 0)kind += GAUCHE_DIR1;
					if(tab[p-1] == 0)kind += HAUT_DIR1;
				}
				break;
				
			case EST:
				if(tab[pos+lar] == 0) kind += DROITE_DIR;
				if(tab[pos-lar] == 0) kind += GAUCHE_DIR;
				if(tab[pos+1] == 0){
					kind += HAUT_DIR;
					int p = pos+1;
					if(tab[p-lar] == 0) kind += GAUCHE_DIR1;
					if(tab[p+lar] == 0)kind += DROITE_DIR1;
					if(tab[p+1] == 0)kind += HAUT_DIR1;
				}
				break;
		}
		
		
		this.rw.render(op, kind);
		
		if(dirVue == NORD) op.drawChar("NORD", 10, 150);
		if(dirVue == SUD) op.drawChar("SUD", 10, 150);
		if(dirVue == EST) op.drawChar("EST", 10, 150);
		if(dirVue == OUEST) op.drawChar("OUEST", 10, 150);
		op.drawChar("Code "+kind, 10, 170);
		op.drawChar("Pos "+pos, 10, 190);
		
		
		for(int i=0;i<laby.getHauteurTable();i++){
			for(int j=0;j<laby.getLargeurTable();j++){
				if(memory[i*lar+j] == 1)this.op.fillRect(Color.black, 0 + j*5 , 200+i*5, 5, 5, 1.0f);
				else if((i*lar+j) == pos) this.op.fillRect(Color.green, 0 + j*5 , 200+i*5, 5, 5, 1.0f);
				else if(memory[i*lar+j] == -1) this.op.fillRect(Color.yellow, 0 + j*5 , 200+i*5, 5, 5, 1.0f);
				else this.op.fillRect(Color.gray, 0 + j*5 , 200+i*5, 5, 5, 1.0f);
			}
		}
	
		
		
	}
	
	private boolean move(int code){
		int[] tab = laby.getTable();
		int lar = laby.getLargeurTable();
		int posInit = pos;
		switch(code){
			case GAUCHE_DIR:
				if(dirVue == NORD && tab[pos-1] == 0) pos -= 1;
				if(dirVue == SUD && tab[pos+1] == 0) pos += 1;
				if(dirVue == EST && tab[pos-lar] == 0) pos -= lar;
				if(dirVue == OUEST && tab[pos+lar] == 0) pos += lar;
				break;
				
			case DROITE_DIR:
				if(dirVue == NORD && tab[pos+1] == 0) pos += 1;
				if(dirVue == SUD && tab[pos-1] == 0) pos -= 1;
				if(dirVue == EST && tab[pos+lar] == 0) pos += lar;
				if(dirVue == OUEST && tab[pos-lar] == 0) pos -= lar;
				break;
				
			case HAUT_DIR:
				if(dirVue == NORD && tab[pos-lar] == 0) pos -= lar;
				if(dirVue == SUD && tab[pos+lar] == 0) pos += lar;
				if(dirVue == EST && tab[pos+1] == 0) pos += 1;
				if(dirVue == OUEST && tab[pos-1] == 0) pos -= 1;
				break;
				
			case BAS_DIR:
				if(dirVue == NORD && tab[pos+lar] == 0) pos += lar;
				if(dirVue == SUD && tab[pos-lar] == 0) pos -= lar;
				if(dirVue == EST && tab[pos-1] == 0) pos -= 1;
				if(dirVue == OUEST && tab[pos+1] == 0) pos += 1;
				break;
		}
		
		if(pos != posInit) return true;
		else return false;
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


	

}

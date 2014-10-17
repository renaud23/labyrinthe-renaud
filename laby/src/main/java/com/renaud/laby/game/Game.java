package com.renaud.laby.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private List<IActivate> activables = new ArrayList<IActivate>();

	private boolean start = false;

	public void activate() {
		if (start){
			for (IActivate a : this.activables){
				if(a.isActive()) a.activate();
			}
		}
	}

	public void addActivable(IActivate a) {
		this.activables.add(a);
	}

	public void removeActivable(IActivate a) {
		this.activables.remove(a);
	}

	public void start() {
		start = true;
	}

	public boolean isStarted() {
		return start;
	}
}

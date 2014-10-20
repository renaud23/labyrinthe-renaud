package com.renaud.laby.game;

import java.util.ArrayList;
import java.util.List;

import com.renaud.laby.controller.IController;

public class Game implements IController {

	private List<IActivate> activables = new ArrayList<IActivate>();

	private boolean locked;

	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;
	protected boolean turnLeft;
	protected boolean turnRight;

	private boolean start = false;

	public void activate() {
		if (start) {
			locked = true;
			for (IActivate a : this.activables) {
				if (a.isActive())
					a.activate();
				if (a instanceof IController) {
					IController c = (IController) a;
					if (up)
						c.up();
					if (left)
						c.left();
					if (right)
						c.right();
					if (down)
						c.down();
					if (turnLeft)
						c.turnLeft();
					if (turnRight)
						c.turnRight();
				}
			}
			locked = false;
			this.up = false;
			this.down = false;
			this.right = false;
			this.left = false;
			this.turnLeft = false;
			this.turnRight = false;
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

	@Override
	public void up() {
		if (!locked)
			up = true;
	}

	@Override
	public void left() {
		if (!locked)
			left = true;
	}

	@Override
	public void down() {
		if (!locked)
			down = true;
	}

	@Override
	public void right() {
		if (!locked)
			right = true;
	}

	@Override
	public void turnRight() {
		if (!locked)
			turnRight = true;

	}

	@Override
	public void turnLeft() {
		if (!locked)
			turnLeft = true;

	}

}

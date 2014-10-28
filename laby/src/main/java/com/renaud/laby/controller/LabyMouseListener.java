package com.renaud.laby.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LabyMouseListener extends MouseAdapter{
	
	private IController controllers;

	public LabyMouseListener(IController controllers) {
		this.controllers = controllers;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.controllers.mouseMoved(e.getX(), e.getY());
	}

}

package com.renaud.laby.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Spliterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.swing.JFrame;

import com.renaud.laby.game.Game;

public class Fenetre extends Observable implements Iterable<IDrawable> {

	private JFrame frame;

	private AWTImageBufferDecorator offscreen;

	private CanvasHwdBuffer buffer;

	private Game game;

	private int largeur;
	private int hauteur;

	private List<IDrawable> drawables = new ArrayList<IDrawable>();

	private Timer timer;

	public Fenetre(int largeur, int hauteur) {
		this.frame = new JFrame("Labyrinthe");
		this.frame.setIgnoreRepaint(true);
		this.frame.setVisible(true);
		this.frame.setPreferredSize(new Dimension(largeur, hauteur));
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.buffer = new CanvasHwdBuffer(largeur, hauteur);
		this.frame.add((Component) this.buffer);

		this.buffer.createStrategy();
		this.frame.pack();
		this.frame.validate();

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.setResizable(false);

		// la boucle de jeu
		this.timer = new Timer();
		this.start();
	}

	public void setIconImage(Image image) {
		this.frame.setIconImage(image);
	}

	public void cleanListener() {
		this.offscreen.cleanListener();
	}

	public void resize(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public void repaint() {
		this.frame.repaint();
	}

	public BufferStrategy getStrategy() {
		return this.buffer.getBufferStrategy();
	}

	public Component getComponent() {
		return this.buffer;
	}

	public IDrawOperation getDrawOperation() {
		return this.buffer;
	}

	private void start() {

		final Fenetre f = this;

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (game != null && game.isStarted()) {
					game.activate();
					f.getDrawOperation().clean();
					for (IDrawable drw : f) {
						if (drw instanceof DrawOperationAware)
							((DrawOperationAware) drw).setDrawOperation(f.getDrawOperation());
						drw.draw();
					}
					f.getStrategy().show();
				}

			}
		};

		this.timer.schedule(task, 0, 10);
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void addDrawable(IDrawable d) {
		this.drawables.add(d);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public Iterator<IDrawable> iterator() {
		return this.drawables.iterator();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void forEach(Consumer<? super IDrawable> action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spliterator<IDrawable> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}

}

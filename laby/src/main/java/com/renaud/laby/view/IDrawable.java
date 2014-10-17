package com.renaud.laby.view;

/**
 * une entit� qui peut dessiner gr�ce � idraweroperation.
 * 
 * @author Renaud
 */
public interface IDrawable {

	public boolean isChange();

	public void setChange();

	public void draw();
}

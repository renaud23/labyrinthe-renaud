package com.renaud.laby.worm.mouvement;

import com.renaud.laby.worm.WormBlockedException;

public interface IWormMouvement{
	public int next() throws WormBlockedException;
}

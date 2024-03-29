package com.renaud.laby.player;

import java.util.HashMap;
import java.util.Map;

import com.renaud.laby.Direction;
import com.renaud.laby.Labyrinthe;
import com.renaud.laby.LabyrintheTools;
import com.renaud.laby.view.IDrawOperation;

public class RenderWallASCII extends RenderWall3D {
	
	/*
	 * 
	 * n 128 s 64 o 32 e 16
	 * 
	 * n 8 s 4 e 2 o 1
	 * 
	 */
	
//	private static int largeur = 15;
	private static int hauteur = 8;
	
	private Map<Integer, String[]> maps = new HashMap<Integer, String[]>();
	

	public RenderWallASCII(Labyrinthe laby, Player player){
		super(laby,player);
		
		
		
		maps.put(0,type_0000);
		maps.put(1,type_0001);
		maps.put(2,type_0002);
		maps.put(3,type_0003);
		maps.put(136,type_0136);
		maps.put(24,type_0024);
		maps.put(153,type_0153);
		maps.put(8,type_0008);
		maps.put(56,type_0056);
		maps.put(184,type_0184);
		maps.put(9,type_0009);
		maps.put(137,type_0137);
		maps.put(168,type_0168);
		maps.put(139,type_0139);
		maps.put(40,type_0040);
		maps.put(138,type_0138);
		maps.put(152,type_0152);
	}
	
	private static String[] type_0000 = {
		"\\             /",
		"#\\___________/#",
		"##|#########|##",
		"##|#########|##",
		"##|#########|##",
		"##|#########|##",
		"#/___________\\#",
		"/_____________\\"
	};

	
	private static String[] type_0001 = {
		"\\              ",
		"#\\_____________",
		"##|############",
		"##|############",
		"##|############",
		"##|############",
		"#/_____________",
		"/______________"
	};

	
	private static String[] type_0002 = {
		"              /",
		"_____________/#",
		"############|##",
		"############|##",
		"############|##",
		"############|##",
		"_____________\\#",
		"______________\\"
	};

	
	private static String[] type_0003 = {
		"               ",
		"_______________",
		"###############",
		"###############",
		"###############",
		"###############",
		"_______________",
		"_______________"
	};

	
	private static String[] type_0008 = {
		"\\             /",
		"#\\           /#",
		"##\\_________/##",
		"###|#######|###",
		"###|#######|###",
		"##/_________\\##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	private static String[] type_0136 = {
		"\\             /",
		"#\\           /#",
		"##\\_________/##",
		"###|       |###",
		"###|_______|###",
		"##/_________\\##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	private static String[] type_0024 = {
		"\\           |##",
		"#\\          |##",
		"##\\_________|##",
		"###|########|##",
		"###|########|##",
		"##/_________|##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	private static String[] type_0153 = {
		"\\           |##",
		"#\\          |##",
		"##\\_________|##",
		"###|        |##",
		"###|________|##",
		"##/_________|##",
		"#/___________\\#",
		"/_____________\\"
	};	
	

	
	private static String[] type_0168 = {
		"##|           /",
		"##|          /#",
		"##|_________/##",
		"##|        |###",
		"##|________|###",
		"##|_________\\##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	private static String[] type_0138 = {
		"              /",
		"__           /#",
		"##|\\_______ /##",
		"##|#|      |###",
		"##|#|______|###",
		"##|/________\\##",
		"_____________\\#",
		"______________\\"
	};
	
	private static String[] type_0040 = {
		"##|           /",
		"##|          /#",
		"##|_________/##",
		"##|#########|##",
		"##|#########|##",
		"##|_________\\##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	
	private static String[] type_0056 = {
		"##|         |##",
		"##|         |##",
		"##|_________|##",
		"##|#########|##",
		"##|#########|##",
		"##|_________|##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	private static String[] type_0184 = {
		"##|         |##",
		"##|         |##",
		"##|_________|##",
		"##|         |##",
		"##|_________|##",
		"##|_________|##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	private static String[] type_0009 = {
		"\\              ",
		"#\\           __",
		"##\\________/|##",
		"###|######|#|##",
		"###|######|#|##",
		"##/________\\|##",
		"#/_____________",
		"/______________"
	};
	
	private static String[] type_0152 = {
		"\\           |##",
		"#\\          |##",
		"##\\_________|##",
		"###|        |##",
		"###|________|##",
		"##/_________|##",
		"#/___________\\#",
		"/_____________\\"
	};
	
	private static String[] type_0137 = {
		"\\              ",
		"#\\           __",
		"##\\________/|##",
		"###|      |#|##",
		"###|______|#|##",
		"##/________\\|##",
		"#/_____________",
		"/______________"
	};
	
	private static String[] type_0139 = {
		"               ",
		"__           __",
		"##|\\_______/|##",
		"##|#|     |#|##",
		"##|#|_____|#|##",
		"##|/_______\\|##",
		"_______________",
		"_______________"
	};
	
	
	
	public void render(IDrawOperation op){
		int first = kind(player.getPosition(),player.getDirectionRegard());
		int second = kind(LabyrintheTools.nextPos(
				laby, this.player.getDirectionRegard(), this.player.getPosition(), 1),
				player.getDirectionRegard()) * (int)Math.pow(2, 4);

		if((first&Direction.HAUT_DIR) != Direction.HAUT_DIR){
			this.print(op, maps.get(first));
		}else{
			this.print(op, maps.get(first+second));
		}
	}
	
	private void print(IDrawOperation op,String[] tab){
		int y = 12;
		if(tab != null){
			for(int i=0;i<hauteur;i++){
				op.drawChar(tab[i],20,y*(1+i));
			}
		}
	}
	
}

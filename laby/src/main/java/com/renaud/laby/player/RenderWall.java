package com.renaud.laby.player;

import java.util.HashMap;
import java.util.Map;

import com.renaud.laby.view.IDrawOperation;

public class RenderWall {
	
	private static int largeur = 15;
	private static int hauteur = 8;
	
	private Map<Integer, String[]> maps = new HashMap<Integer, String[]>();
	
	

	public RenderWall(){
		maps.put(0,type_0000);
		maps.put(1,type_0001);
		maps.put(2,type_0002);
		maps.put(3,type_0003);
		maps.put(11,type_0011);
		maps.put(8,type_0008);
		maps.put(10,type_0010);
		maps.put(9,type_0009);
	}

	
	
	private static String[] type_0000 = {
		"+             /",
		"#+___________/#",
		"##|#########|##",
		"##|#########|##",
		"##|#########|##",
		"##|#########|##",
		"#/___________+#",
		"/_____________+"
	};

	
	private static String[] type_0001 = {
		"+              ",
		"#+_____________",
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
		"_____________+#",
		"______________+"
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
		"+             /",
		"#+___________/#",
		"##|         |##",
		"##|         |##",
		"##|         |##",
		"##|_________|##",
		"#/___________+#",
		"/_____________+"
	};
	
	private static String[] type_0009 = {
		"+              ",
		"#+_____________",
		"##|            ",
		"##|            ",
		"##|            ",
		"##|____________",
		"#/_____________",
		"/______________"
	};	
	

	
	private static String[] type_0010 = {
		"              /",
		"_____________/#",
		"            |##",
		"            |##",
		"            |##",
		"____________|##",
		"_____________+#",
		"______________+"
	};
	
	private static String[] type_0011 = {
		"               ",
		"_______________",
		"               ",
		"               ",
		"               ",
		"_______________",
		"_______________",
		"_______________"
	};
	
	
	public void render(IDrawOperation op,int code){
		String[] tab = maps.get(code);
	
		int y = 12;
		if(tab != null){
			for(int i=0;i<hauteur;i++){
				//System.out.println(tab[i]);
				op.drawChar(tab[i],20,y*(1+i));
			}
		}
	}
}
	
	
	
	
	
	
	
	
	


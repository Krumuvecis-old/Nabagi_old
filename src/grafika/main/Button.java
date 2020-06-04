package grafika.main;

import java.awt.Color;

class Button {
	
	protected int x, y, wx, wy; //koordinâtas un platums&augstums
	protected String title; //teksts izvadei
	protected int correction;
	protected boolean active=false, pressed=false, result=false; //statusi darbîbâm
	
	protected static Color
			bodyColorDefault=Color.lightGray, bodyColorPressed=Color.green, bodyColorActive=Color.lightGray, 
			contourColorDefault=Color.darkGray, contourColorPressed=Color.yellow, contourColorActive=Color.yellow,
			textColorDefault=Color.black, textColorPressed=Color.black, textColorActive=Color.black;
	
	protected static int burtaPlatums=7, burtaAugstums=12;
	
	protected void actions(SetupThread thread) {
		
		if (thread.input.xPele>=x && thread.input.xPele<=(x+wx) &&
				thread.input.yPele>=y && thread.input.yPele<=(y+wy)) { //pârbauda vai kursors ir virs pogas
			active=true;
		} else active=false;
		
		if (pressed && !thread.input.peleClick) result=true; //atlaişot pogu kursoram esot virs tâs, poga nostrâdâ
		if (active && thread.input.peleClick) pressed=true; else pressed=false; //ja noklikğíina virs aktîvas pogas, tâ tiek nospiesta
		
	}
	
}

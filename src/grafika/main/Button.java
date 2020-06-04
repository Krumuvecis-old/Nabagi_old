package grafika.main;

import java.awt.Color;

class Button {
	
	protected int x, y, wx, wy; //koordin�tas un platums&augstums
	protected String title; //teksts izvadei
	protected int correction;
	protected boolean active=false, pressed=false, result=false; //statusi darb�b�m
	
	protected static Color
			bodyColorDefault=Color.lightGray, bodyColorPressed=Color.green, bodyColorActive=Color.lightGray, 
			contourColorDefault=Color.darkGray, contourColorPressed=Color.yellow, contourColorActive=Color.yellow,
			textColorDefault=Color.black, textColorPressed=Color.black, textColorActive=Color.black;
	
	protected static int burtaPlatums=7, burtaAugstums=12;
	
	protected void actions(SetupThread thread) {
		
		if (thread.input.xPele>=x && thread.input.xPele<=(x+wx) &&
				thread.input.yPele>=y && thread.input.yPele<=(y+wy)) { //p�rbauda vai kursors ir virs pogas
			active=true;
		} else active=false;
		
		if (pressed && !thread.input.peleClick) result=true; //atlai�ot pogu kursoram esot virs t�s, poga nostr�d�
		if (active && thread.input.peleClick) pressed=true; else pressed=false; //ja noklik��ina virs akt�vas pogas, t� tiek nospiesta
		
	}
	
}

package localClient.grafika.grafikaModes.playerView.playerViewVecais;

import java.awt.Color;

public class Button {
	
	public int x, y, wx, wy; //koordin�tas un platums&augstums
	public String title; //teksts izvadei
	public int correction;
	public boolean active=false, pressed=false, result=false; //statusi darb�b�m
	
	public static Color
			bodyColorDefault=Color.lightGray, bodyColorPressed=Color.green, bodyColorActive=Color.lightGray, 
			contourColorDefault=Color.darkGray, contourColorPressed=Color.yellow, contourColorActive=Color.yellow,
			textColorDefault=Color.black, textColorPressed=Color.black, textColorActive=Color.black;
	
	public static int burtaPlatums=7, burtaAugstums=12;
	
	public void actions(PlayerThread thread) {
		
		if (thread.input.xPele>=x && thread.input.xPele<=(x+wx) &&
				thread.input.yPele>=y && thread.input.yPele<=(y+wy)) { //p�rbauda vai kursors ir virs pogas
			active=true;
		} else active=false;
		
		if (pressed && !thread.input.peleClick) result=true; //atlai�ot pogu kursoram esot virs t�s, poga nostr�d�
		if (active && thread.input.peleClick) pressed=true; else pressed=false; //ja noklik��ina virs akt�vas pogas, t� tiek nospiesta
		
	}
	
}

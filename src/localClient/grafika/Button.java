package localClient.grafika;

import localClient.ClientThread;
import localClient.grafika.grafikaParts.SamplePanel;

;
import java.awt.*;
import java.util.ArrayList;

public class Button {
	
	public int x, y, wx, wy; //koordinâtas un platums&augstums
	public boolean topAlignment, leftAlignment;
	public String title; //teksts izvadei un reference darbîbai
	public int titleCorrection;
	public boolean active=false, pressed=false, result=false; //statusi darbîbâm
	public ActionReference reference;

	public enum ActionReference {
		head1, head2, head3, head4, head5, head6, head7, head8, head9,
		left1, left2, left3, left4, left5, left6, left7, left8, left9,
		center1, center2, center3, center4, center5, center6, center7, center8, center9,
		right1, right2, right3, right4, right5,  right6, right7, right8, right9,
		foot1, foot2, foot3, foot4, foot5,  foot6, foot7, foot8, foot9
	}

	private Button(int[] xy, boolean alignFromTop, boolean alignFromLeft,
				   int[] size, ActionReference actionReference, String buttonTitle, int titleCorrectionX){
		x = xy[0];
		if (!alignFromLeft) x += size[0];
		y = xy[1];
		if (!alignFromTop) y += size[1];
		topAlignment = alignFromTop;
		leftAlignment = alignFromLeft;
		wx = size[0];
		wy = size[1];
		reference = actionReference;
		title = buttonTitle;
		titleCorrection = titleCorrectionX;
		active = false;
		pressed = false;
		result = false;
	}

	public static class ButtonDetails {
		ActionReference reference;
		String title;
		int correction;
		public ButtonDetails(ActionReference actionReference, String buttonTitle, int xCorrection){
			reference = actionReference;
			title = buttonTitle;
			correction = xCorrection;
		}
	}

	public static void addButtonList(SamplePanel samplePanel, boolean vertical,
									 int[] offsetXY, boolean alignFromTop, boolean alignFromLeft,
									 int[] buttonSize, int spacing,
									 ArrayList<ButtonDetails> buttonDetailList){
		for (int i=0; i<buttonDetailList.size(); i++){
			int[] buttonXY = new int[]{offsetXY[0], offsetXY[1]};

			if (vertical) buttonXY[1] += (buttonSize[1] + spacing) * i; //vertikâla pogu kolonna
			else buttonXY[0] += (buttonSize[0] + spacing) * i; //horizontâla pogu rinda

			samplePanel.buttonList.add(new Button(buttonXY, alignFromTop, alignFromLeft, buttonSize,
					buttonDetailList.get(i).reference, buttonDetailList.get(i).title, buttonDetailList.get(i).correction));
		}
	}

	public static void drawButtons(Graphics g, SamplePanel samplePanel) {

		ArrayList<Button> buttonList = samplePanel.buttonList;
		int[] panelOffsetXY = samplePanel.XY;
		int[] panelSize = samplePanel.size;

		@SuppressWarnings("unused")
		Color
				bodyColorDefault= Color.lightGray, bodyColorPressed= Color.green, bodyColorActive= Color.lightGray,
				contourColorDefault= Color.darkGray, contourColorPressed= Color.yellow, contourColorActive= Color.yellow,
				textColorDefault= Color.black, textColorPressed= Color.black, textColorActive= Color.black;

		int burtaPlatums = 7, burtaAugstums = 12;

		for (int i=0; i<buttonList.size(); i++) {

			int x, y, wx = buttonList.get(i).wx, wy = buttonList.get(i).wy;

			if(buttonList.get(i).leftAlignment) x = panelOffsetXY[0] + buttonList.get(i).x; //aligned by left side
			else x = panelOffsetXY[0] + panelSize[0] - buttonList.get(i).x; //aligned by right side

			if(buttonList.get(i).topAlignment) y = panelOffsetXY[1] + buttonList.get(i).y; //aligned by top side
			else y = panelOffsetXY[1] + panelSize[1] - buttonList.get(i).y; //aligned by bottom side


			boolean active = buttonList.get(i).active, pressed = buttonList.get(i).pressed;

			if (pressed) g.setColor(bodyColorPressed); else g.setColor(bodyColorDefault);
			g.fillRect(x, y, wx, wy); //zîmç paðu pogu

			if (active) g.setColor(contourColorActive); else g.setColor(contourColorDefault);
			g.drawRect(x, y, wx, wy); //zîmç kontûru

			String teksts = buttonList.get(i).title;
			int tekstaGarums = buttonList.get(i).title.length();
			int x0 = x + wx/2 - tekstaGarums*burtaPlatums/2, y0 = y + wy/2 + burtaAugstums/2, //teksta sâkumpunkts, lai teksts bûtu pogai pa vidu
					correction = buttonList.get(i).titleCorrection;

			g.setColor(textColorDefault);
			g.drawString(teksts, x0 + correction, y0); //pogas nosaukums
			//g.drawRect(x0, y0-burtaAugstums, tekstaGarums*burtaPlatums, burtaAugstums); //râmîtis ap tekstu

		}

	}

	public static void checkButtonActions(ClientThread thread){
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).header);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).footer);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).panelL);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).panelR);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).centerPanel);
	}

	private static void checkButtonList(ClientThread thread, SamplePanel samplePanel){
		for (int i = 0; i<samplePanel.buttonList.size(); i++) {
			samplePanel.buttonList.get(i).activityCheck(thread, samplePanel.XY, samplePanel.size); //pârbauda katras pogas statusu
			if (samplePanel.buttonList.get(i).result) { //ja poga nostrâdâjusi
				thread.dati.drawManagerList.get(thread.dati.modeCurrent).inputActions.buttonActions(samplePanel.buttonList.get(i).reference, thread); //notikums
				samplePanel.buttonList.get(i).result = false; //reseto pogas statusu
			}
		}
	}

	private void activityCheck(ClientThread thread, int[] panelOffset, int[] panelSize) {
		int[] pogaXY = new int[]{panelOffset[0], panelOffset[1]};

		if (leftAlignment) pogaXY[0] += x;
		else pogaXY[0] += panelSize[0] - x;

		if (topAlignment) pogaXY[1] += y;
		else pogaXY[1] += panelSize[1] - y;

		//pârbauda vai kursors ir virs pogas
		active = thread.input.xPele >= pogaXY[0] &&
				thread.input.xPele <= (pogaXY[0] + wx) &&
				thread.input.yPele >= pogaXY[1] &&
				thread.input.yPele <= (pogaXY[1] + wy);

		if (pressed && !thread.input.peleClick) result=true; //atlaiþot pogu kursoram esot virs tâs, poga nostrâdâ
		pressed = active && thread.input.peleClick; //ja noklikðíina virs aktîvas pogas, tâ tiek nospiesta
	}



	
}

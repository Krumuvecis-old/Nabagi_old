package localClient.grafika;

import localClient.ClientThread;
import localClient.grafika.grafikaParts.InputActions;
import localClient.grafika.grafikaParts.SamplePanel;

import java.awt.*;
import java.util.ArrayList;

public class Button {
	
	public int x, y, wx, wy; //koordinâtas un platums&augstums
	public boolean topAlignment, leftAlignment;
	public String title; //teksts izvadei un reference darbîbai
	public int titleCorrection;
	public boolean active=false, pressed=false, result=false; //statusi darbîbâm
	public int reference;

	private Button(int[] xy, boolean alignFromTop, boolean alignFromLeft,
				   int[] size, int actionReference, String buttonTitle, int titleCorrectionX){
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
		int reference;
		String title;
		int correction;
		public ButtonDetails(int actionReference, String buttonTitle, int xCorrection){
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
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).header, PanelType.header);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).footer, PanelType.footer);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).panelL, PanelType.left);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).panelR, PanelType.right);
		checkButtonList(thread, thread.dati.drawManagerList.get(thread.dati.modeCurrent).centerPanel, PanelType.center);
	}

	public enum PanelType {
		header,
		footer,
		left,
		right,
		center
	}

	private static void checkButtonList(ClientThread thread, SamplePanel samplePanel, PanelType panelType){
		for (int i = 0; i<samplePanel.buttonList.size(); i++) {
			samplePanel.buttonList.get(i).activityCheck(thread, samplePanel.XY, samplePanel.size); //pârbauda katras pogas statusu
			if (samplePanel.buttonList.get(i).result) { //ja poga nostrâdâjusi
				buttonActivitySelector(thread, samplePanel, i, panelType); //atrod atbilstoðo darbîbu pogai
				samplePanel.buttonList.get(i).result = false; //reseto pogas statusu
			}
		}
	}

	private static void buttonActivitySelector(ClientThread thread, SamplePanel samplePanel, int pogasNr, PanelType panelType){
		InputActions inputActions = thread.dati.drawManagerList.get(thread.dati.modeCurrent).inputActions;

		switch (panelType){
			case header ->
					inputActions.headerButtonActions(
							samplePanel.buttonList.get(pogasNr).reference, thread);
			case footer ->
					inputActions.footerButtonActions(
							samplePanel.buttonList.get(pogasNr).reference, thread);
			case left ->
					inputActions.leftButtonActions(
							samplePanel.buttonList.get(pogasNr).reference, thread);
			case right ->
					inputActions.rightButtonActions(
							samplePanel.buttonList.get(pogasNr).reference, thread);
			case center ->
					inputActions.centerButtonActions(
							samplePanel.buttonList.get(pogasNr).reference, thread);

			default -> System.out.println("Undefined panel detected!");
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

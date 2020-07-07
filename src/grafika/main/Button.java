package grafika.main;

import calculations.KonstantesUniversal;
import grafika.main.grafikaParts.SamplePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Button {
	
	public int x, y, wx, wy; //koordinâtas un platums&augstums
	public boolean topAlignment, leftAlignment;
	public String[] title; //teksts izvadei un reference darbîbai
	public int titleCorrection;
	public boolean active=false, pressed=false, result=false; //statusi darbîbâm

	private Button(int[] xy, boolean alignFromTop, boolean alignFromLeft,
				   int[] size, String buttonTitle, String actionReference, int titleCorrectionX){
		x = xy[0];
		if (!alignFromLeft) x += size[0];
		y = xy[1];
		if (!alignFromTop) y += size[1];
		topAlignment = alignFromTop;
		leftAlignment = alignFromLeft;
		wx = size[0];
		wy = size[1];
		title = new String[]{buttonTitle, actionReference};
		titleCorrection = titleCorrectionX;
		active = false;
		pressed = false;
		result = false;
	}

	public static void addButtonList(SamplePanel samplePanel, boolean vertical,
									 int[] offsetXY, boolean alignFromTop, boolean alignFromLeft,
									 int[] buttonSize, int spacing,
									 String[][] namesList){
		for (int i=0; i<namesList.length; i++){
			int[] buttonXY = new int[]{offsetXY[0], offsetXY[1]};

			if (vertical) buttonXY[1] += (buttonSize[1] + spacing) * i; //vertikâla pogu kolonna
			else buttonXY[0] += (buttonSize[0] + spacing) * i; //horizontâla pogu rinda

			samplePanel.buttonList.add(new Button(buttonXY, alignFromTop, alignFromLeft,
					buttonSize, namesList[i][0], namesList[i][1], 0));
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

			String teksts = buttonList.get(i).title[0];
			int tekstaGarums = buttonList.get(i).title[0].length();
			int x0 = x + wx/2 - tekstaGarums*burtaPlatums/2, y0 = y + wy/2 + burtaAugstums/2, //teksta sâkumpunkts, lai teksts bûtu pogai pa vidu
					correction = buttonList.get(i).titleCorrection;

			g.setColor(textColorDefault);
			g.drawString(teksts, x0 + correction, y0); //pogas nosaukums
			//g.drawRect(x0, y0-burtaAugstums, tekstaGarums*burtaPlatums, burtaAugstums); //râmîtis ap tekstu

		}

	}

	public static void checkButtonActions(SetupThread thread){
		checkButtonList(thread, thread.dati.drawManager.header.samplePanel);
		checkButtonList(thread, thread.dati.drawManager.footer.samplePanel);
		checkButtonList(thread, thread.dati.drawManager.panel1.samplePanel);
		checkButtonList(thread, thread.dati.drawManager.panel2.samplePanel);
		checkButtonList(thread, thread.dati.drawManager.panel3.samplePanel);
	}

	private static void checkButtonList(SetupThread thread, SamplePanel samplePanel){
		for (int i = 0; i<samplePanel.buttonList.size(); i++) {
			samplePanel.buttonList.get(i).activityCheck(thread, samplePanel.XY, samplePanel.size); //pârbauda katras pogas statusu
			if (samplePanel.buttonList.get(i).result) { //ja poga nostrâdâjusi
				buttonActions(samplePanel.buttonList.get(i).title[1], thread); //notikums
				samplePanel.buttonList.get(i).result=false; //reseto pogas statusu
			}
		}
	}

	private void activityCheck(SetupThread thread, int[] panelOffset, int[] panelSize) {
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


	private static void buttonActions(String reference, SetupThread thread){
		switch (reference) {

			//zemâk header pogu notikumi

			case "head1" -> thread.running = false;
			case "head2" -> thread.grafika.ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
			case "head3" -> thread.grafika.ekrans.setState(Frame.ICONIFIED);
			case "head4" -> System.out.println("nospiesta header ceturtâ poga");
			case "head5" -> System.out.println("nospiesta header piektâ poga");

			//zemâk footer pogu notikumi

			case "foot1" -> thread.dati.drawLayoutGrid = !thread.dati.drawLayoutGrid;
			case "foot2" -> thread.dati.drawInputDiagnosticsPanel = !thread.dati.drawInputDiagnosticsPanel;
			case "palette0poga" -> thread.dati.colorPalette.pickPreset(0);
			case "palette1poga" -> thread.dati.colorPalette.pickPreset(1);
			case "palette2poga" -> thread.dati.colorPalette.pickPreset(2);
			case "loadSettingsPoga" -> FileHandler.loadSettings(thread.dati, thread.grafika.ekrans);
			case "saveSettingsPoga" -> FileHandler.saveSettings(thread.dati);

			//zemâk pirmâ paneïa pogu notikumi

			case "panel1poga1" -> newClient(); //jauns klients
			case "panel1poga2" -> System.out.println("nospiesta panel1 otrâ poga");
			case "panel1poga3" -> System.out.println("nospiesta panel1 treðâ poga");
			case "panel1poga4" -> System.out.println("nospiesta panel1 ceturtâ poga");
			case "panel1poga5" -> System.out.println("nospiesta panel1 piektâ poga");

			//zemâk otrâ paneïa pogu notikumi

			case "panel2poga1" -> System.out.println("nospiesta panel2 pirmâ poga");
			case "panel2poga2" -> System.out.println("nospiesta panel2 otrâ poga");
			case "panel2poga3" -> System.out.println("nospiesta panel2 treðâ poga");
			case "panel2poga4" -> System.out.println("nospiesta panel2 ceturtâ poga");
			case "panel2poga5" -> System.out.println("nospiesta panel2 piektâ poga");

			//zemâk treðâ paneïa pogu notikumi

			case "zoomInPoga" -> System.out.println("zoom++ placeholder");
			case "zoomOutPoga" -> System.out.println("zoom-- placeholder");
			case "zvaigznePoga" -> thread.dati.drawSampleImages = !thread.dati.drawSampleImages;
			case "panel3poga4" -> System.out.println("nospiesta panel3 ceturtâ poga");
			case "panel3poga5" -> System.out.println("nospiesta panel3 piektâ poga");
			default -> System.out.println("ServerUI reìistrçts nedefinçtas pogas notikums!");
		}

		//zemâk vecâs darbîbas

//		if (i==0) { //pirmâ poga
//			calculations.Main.pauze=!calculations.Main.pauze;
//
//		} else if (i==1) { //otrâ poga
//			dati.startPlayerView(false);
//
//		} else if (i==2) { //treðâ poga
//			dati.startPlayerView(true);
//
//		} else if (i==3) { //ceturtâ poga
//			dati.tablo1Draw=!dati.tablo1Draw;
//
//		} else if (i==4) { //piektâ poga
//			dati.tablo2Draw=!dati.tablo2Draw;
//
//		} else if (i==5) { //sestâ poga
//			dati.tablo3Draw=!dati.tablo3Draw;
//
//		} else if (i==6) { //septîtâ poga
//			dati.miniMapDraw=!dati.miniMapDraw;
//
//		} else if (i==7) { //astotâ poga
//			dati.inputPanelDraw=!dati.inputPanelDraw;
//
//		} else if (i==8) { //devîtâ poga
//			dati.colorPanelDraw=!dati.colorPanelDraw;
//
//		} else if (i==9) { //desmitâ poga
//			KonstantesUniversal.overallGenRate+=0.01;
//
//		} else if (i==10) { //vienpadsmitâ poga
//			KonstantesUniversal.overallGenRate-=0.01;
//
//		}


	}
	
}

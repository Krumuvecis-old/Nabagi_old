package localClient.grafika.grafikaModes.playerView.playerViewVecais;

import java.awt.Color;
import java.util.ArrayList;

import localClient.grafika.GrafikasDati;
import server.calculations.cilveki.Cilveks;
import localClient.grafika.grafikaModes.playerView.playerViewVecais.centerPanel.DrawCenterPanel;
import localClient.grafika.grafikaModes.playerView.playerViewVecais.sidePanels.DrawSidePanels;

public class Dati {
	public server.calculations.CalculationTimeCalculator calculationTimeCalculator = new server.calculations.CalculationTimeCalculator();

	public boolean lobby=false;

	public boolean playerDead=false;
	public String playerName;
	public Cilveks player;

	protected static Color fonaKrasa=Color.black, nosaukumaKrasa=Color.gray;
	protected static int nosaukumsX=5, nosaukumsY=15;
	protected boolean fullscreen;
	public int ekranaPlatums, ekranaAugstums;

	public ArrayList<Button> buttonList;


	// --------------------
	//zem�k par kartes z�m��anu

	public DrawCenterPanel drawCenterPanel = new DrawCenterPanel();
	public DrawSidePanels drawSidePanels = new DrawSidePanels();

	//protected boolean karteDrawInfo=true; //zem kartes var izvad�t datus par izm�riem

	public static int karteNobideX=15, karteNobideY=nosaukumsY*2; //laukuma nob�de ekr�n�


	public static int karteAtstatumsX=20, karteAtstatumsY=60;
	public int kartePlatums;

	//protected int kartesPlatums, kartesAugstums;
	protected double merogs;

	public boolean drawCrosshair=true;
	public static int crosshairSize=30;

	// --------------------
	//zem�k par loot z�m��anu

	public boolean lietasDrawInfo=false; //papildinform�cijas z�m��ana

	// --------------------
	//zem�k par cilv�ku z�m��anu

	public boolean cilvekiDrawInfo=false; //papildinform�cijas un redzesloku z�m��ana

	public boolean cilvekiDrawR=false;


	// --------------------
	//zem�k par input testa paneli

	public boolean diagnosticsPanelDraw=true;
	public static Color diagnosticsPanelColor=nosaukumaKrasa;
	public static int diagnosticPanelPlatums=150, diagnosticPanelAugstums=200;
	protected int diagnosticsPanelWx=150,
			diagnosticsPanelX=ekranaPlatums-diagnosticsPanelWx-5, diagnosticsPanelY=30;


	protected void initialize(PlayerThread thread) {

		boolean MINI=true; //mini versija
		boolean TV=false; //ir vai nav piesl�gts televizoram

		if (MINI) {
			fullscreen=false; //mini versija
			ekranaPlatums=1000;
			ekranaAugstums=700;
		} else if (TV) {
			fullscreen=false; //televizoram
			ekranaPlatums=1700;
			ekranaAugstums=1000;
		} else {
			fullscreen=true; //portat�vajam - default
			ekranaPlatums=1350;
			ekranaAugstums=700;
		}

		int kartePlatumsMax=ekranaPlatums-karteNobideX-karteAtstatumsX,
				karteAugstumsMax=ekranaAugstums-karteNobideY-karteAtstatumsY;

		kartePlatums=Math.min(kartePlatumsMax, karteAugstumsMax);


		// --------------------
		//par pog�m

		buttonList = new ArrayList<Button>();

		int pogasX0=karteNobideX*2+kartePlatums, pogasY0=karteNobideY,
				pogasPlatums=100, pogasAugstums=30, pogasSpraugaY=5, w=0;

		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Pauze",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"CilvekuKonstantes info",10); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Redzesloki",3); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Loot info",5); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Reset",0); w++;


	}

	private void addButton(int x, int y, int wx, int wy, String title, int correction) {

		int i = buttonList.size();
		buttonList.add(new Button());
		buttonList.get(i).x=x;
		buttonList.get(i).y=y;
		buttonList.get(i).wx=wx;
		buttonList.get(i).wy=wy;
		buttonList.get(i).title=title;
		buttonList.get(i).correction=correction;
		buttonList.get(i).active=false;
		buttonList.get(i).pressed=false;
		buttonList.get(i).result=false;

	}

}

package grafika.player;

import java.awt.Color;
import java.util.ArrayList;

import calculations.cilveki.Cilveks;

class Dati {
	protected konstantes.CalculationTimeCalculator calculationTimeCalculator = new konstantes.CalculationTimeCalculator();
	
	protected boolean lobby=false, playerDead=false;
	public String playerName;
	protected Cilveks player;
		
	protected String windowTitleDefault=konstantes.Parametri.ekranaNosaukums+", PlayerView";
	protected String windowTitle;
	protected static Color fonaKrasa=Color.black, nosaukumaKrasa=Color.gray;
	protected static int nosaukumsX=5, nosaukumsY=15;
	protected boolean fullscreen;
	protected int ekranaPlatums, ekranaAugstums;
	
	protected ArrayList<Button> buttonList;
	
	
	// --------------------
	//zemâk par kartes zîmçðanu
	
	protected DrawMap drawMap = new DrawMap();
	
	//protected boolean karteDrawInfo=true; //zem kartes var izvadît datus par izmçriem
	
	protected static int karteNobideX=15, karteNobideY=nosaukumsY*2, karteAtstatumsX=20, karteAtstatumsY=60; //laukuma nobîde ekrânâ
	protected int kartePlatums;
	
	//protected int kartesPlatums, kartesAugstums;
	protected double merogs;
	
	protected boolean drawCrosshair=true;
	protected static int crosshairSize=30;
	
	// --------------------
	//zemâk par loot zîmçðanu
	
	protected boolean lietasDrawInfo=false; //papildinformâcijas zîmçðana
	
	// --------------------
	//zemâk par cilvçku zîmçðanu
	
	protected boolean cilvekiDrawInfo=false, cilvekiDrawR=false; //papildinformâcijas un redzesloku zîmçðana
	
	
	// --------------------
	//zemâk par input testa paneli
	
	protected boolean diagnosticsPanelDraw=true;
	protected static Color diagnosticsPanelColor=nosaukumaKrasa;
	protected static int diagnosticPanelPlatums=150, diagnosticPanelAugstums=200;
	protected int diagnosticsPanelWx=150,
			diagnosticsPanelX=ekranaPlatums-diagnosticsPanelWx-5, diagnosticsPanelY=30;
	
	
	protected void initialize(PlayerThread thread) {
		
		boolean MINI=true; //mini versija
		boolean TV=false; //ir vai nav pieslçgts televizoram
		
		if (MINI) {
			fullscreen=false; //mini versija
			ekranaPlatums=1000;
			ekranaAugstums=700;
		} else if (TV) {
			fullscreen=false; //televizoram
			ekranaPlatums=1700;
			ekranaAugstums=1000;
		} else {
			fullscreen=true; //portatîvajam - default
			ekranaPlatums=1350;
			ekranaAugstums=700;
		}
		
		int kartePlatumsMax=ekranaPlatums-karteNobideX-karteAtstatumsX,
				karteAugstumsMax=ekranaAugstums-karteNobideY-karteAtstatumsY;
		
		kartePlatums=Math.min(kartePlatumsMax, karteAugstumsMax);
		
		
		// --------------------
		//par pogâm
		
		buttonList = new ArrayList<Button>();
		
		int pogasX0=karteNobideX*2+kartePlatums, pogasY0=karteNobideY, pogasPlatums=100, pogasAugstums=30, pogasSpraugaY=5, w=0;
		
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Pauze",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Cilveku info",10); w++;
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
	
	protected void playerInitialize(PlayerThread thread, boolean primary) { //rada jauna spçlçtâja referenci
		playerDead=false;
		
		if (!primary) {
			playerName=galvenais.Dati.cilvekiList.get(0).vards;
		}
		
		findPlayer(thread);
		windowTitle=windowTitleDefault+" ("+playerName+")"; //nomaina loga nosaukumu
	}
	
	protected int findPlayer(PlayerThread thread) {
		int number=-1;
		for (int i=0; i<galvenais.Dati.cilvekiList.size();i++) {
			if (galvenais.Dati.cilvekiList.get(i).vards==playerName) {
				number=i;
				player=galvenais.Dati.cilvekiList.get(i);
				break;
			}
		}
		return number;
	}
	
	protected void playerDead() {
		playerDead=true;
	}
	
	protected void updateGraphicsValues() {
		
	}
	
}

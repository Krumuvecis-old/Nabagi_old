package grafika.main;

import java.util.ArrayList;

class Dati {
	
	protected String windowTitle=konstantes.Parametri.ekranaNosaukums+", PlayerView";
	
	protected boolean fullscreen;
	protected int ekranaPlatums, ekranaAugstums;
	
	protected ArrayList<Button> buttonList;
	
	protected int nobideX=130, nobideY=10, nobideXR=50, nobideYB=100; //laukuma nobîde ekrânâ
	
	// --------------------
	//zemâk par loot zîmçðanu
	
	protected boolean lietasDrawInfo=false; //papildinformâcijas zîmçðana
	
	// --------------------
	//zemâk par cilvçku zîmçðanu
	
	protected boolean cilvekiDrawInfo=false, cilvekiDrawR=false; //papildinformâcijas un redzesloku zîmçðana
	
	
	
	protected void initialize(PlayerThread thread) {
		windowTitle=windowTitle+" ("+thread.playerName+")";
		
		boolean MINI=false; //mini versija
		boolean TV=false; //ir vai nav pieslçgts televizoram
		
		if (MINI) {
			fullscreen=false; //mini versija
			ekranaPlatums=600;
			ekranaAugstums=400;
		} else if (TV) {
			fullscreen=false; //televizoram
			ekranaPlatums=1700;
			ekranaAugstums=1000;
		} else {
			fullscreen=true; //portatîvajam - default
			ekranaPlatums=1350;
			ekranaAugstums=700;
		}
		
		
		// --------------------
		//par pogâm
		
		buttonList = new ArrayList<Button>();
		
		int pogasX0=5, pogasY0=20, pogasPlatums=100, pogasAugstums=30, pogasSprauga=5, w=0;
		
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Pauze",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Cilveku info",10); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Redzesloki",3); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Loot info",5); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Reset",0); w++;
		
		
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

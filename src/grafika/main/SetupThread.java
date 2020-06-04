package grafika.main;

import konstantes.Parametri;

public class SetupThread implements Runnable{
	
	private static Thread thread;
	private static String threadName;
	public boolean running, minimized, windowActive;
	
	protected Dati dati;
	protected Grafika grafika;
	protected Input input;
	
	
	public void start() {
		dati = new Dati();
		dati.initialize(); //zîmçðana var bût atkarîga no datiem
		
		int maxFrameRate=50; //kadri sekundç
		dati.calculationTimeCalculator.delayMin=1000/maxFrameRate; //simulâcijas solis
		
		threadName=dati.windowTitle;
		thread = new Thread(this, threadName);
		running = true;
		minimized = false;
		windowActive = true;
		
		grafika = new Grafika();
		grafika.initialize(this); //zîmçðana
		
		input = new Input();
		input.initialize(this); //nav jâzîmç, tâpçc pçc grafikas
		
		
		thread.start();
	}

	@Override
	public void run() {
		
		while (running) {
			dati.calculationTimeCalculator.time(true);
			
			if (!minimized) {
				dati.playerFocusFind();
				
				buttonActions();
				
				if (windowActive) {} //aprçíini
				
				grafika.main(this); //tikai zîmçðana, nekâdi aprçíini
			}
			
			dati.calculationTimeCalculator.time(false);
			try{
				Thread.sleep(dati.calculationTimeCalculator.sleepT());
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		grafika.ekrans.dispose();
		System.out.println("thread finished");
		
	}
	
	private void buttonActions() {
		
		for (int i=0; i<dati.buttonList.size();i++) {
			
			dati.buttonList.get(i).actions(this); //pârbauda katras pogas statusu
			
			if (dati.buttonList.get(i).result) { //ja poga nostrâdâjusi
				
				if (i==0) { //pirmâ poga
					galvenais.Main.pauze=!galvenais.Main.pauze;
					
				} else if (i==1) { //otrâ poga
					dati.startPlayerView(false);
					
				} else if (i==2) { //treðâ poga
					dati.startPlayerView(true);
					
				} else if (i==3) { //ceturtâ poga
					dati.tablo1Draw=!dati.tablo1Draw;
					
				} else if (i==4) { //piektâ poga
					dati.tablo2Draw=!dati.tablo2Draw;
					
				} else if (i==5) { //sestâ poga
					dati.miniMapDraw=!dati.miniMapDraw;
					
				} else if (i==6) { //septîtâ poga
					dati.inputPanelDraw=!dati.inputPanelDraw;
					
				} else if (i==7) { //astotâ poga
					dati.colorPanelDraw=!dati.colorPanelDraw;
					
				} else if (i==8) { //devîtâ poga
					Parametri.goldGenRate+=0.01;
					Parametri.paikaGenRate+=0.02;
					
				} else if (i==9) { //desmitâ poga
					Parametri.goldGenRate-=0.01;
					Parametri.paikaGenRate-=0.02;
					
				}
				
				dati.buttonList.get(i).result=false; //reseto pogas statusu
			}
		}
	}
	
	
}

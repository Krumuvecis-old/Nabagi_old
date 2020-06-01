package grafika.setup;

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
			
			//if (dati.k>=Dati.kmax) running=false; //izbeidz ciklu, kad k sasniedz kmax
			
			
			if (!minimized) {
				
				buttonActions();
				
				if (windowActive) {} //aprçíini
				
				grafika.main(this); //tikai zîmçðana, nekâdi aprçíini
			}
			
			
			try{
				Thread.sleep(20);
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
					grafika.main.PlayerThread threadTemp=new grafika.main.PlayerThread();
					threadTemp.playerName=dataBase.Dati.cilvekiList.get(0).vards;
					threadTemp.start();
					
				} else if (i==2) { //treðâ poga
					dati.tablo1Draw=!dati.tablo1Draw;
					
				} else if (i==3) { //ceturtâ poga
					dati.tablo2Draw=!dati.tablo2Draw;
					
				} else if (i==4) { //piektâ poga
					dati.miniMapDraw=!dati.miniMapDraw;
					
				} else if (i==5) { //sestâ poga
					dati.inputPanelDraw=!dati.inputPanelDraw;
					
				} else if (i==6) { //septîtâ poga
					dati.colorPanelDraw=!dati.colorPanelDraw;
					
				} else if (i==7) { //astotâ poga
					Parametri.goldGenRate+=0.002;
					Parametri.paikaGenRate+=0.005;
					
				} else if (i==8) { //devîtâ poga
					Parametri.goldGenRate-=0.002;
					Parametri.paikaGenRate-=0.005;
					
				}
				
				dati.buttonList.get(i).result=false; //reseto pogas statusu
			}
		}
	}
	
}

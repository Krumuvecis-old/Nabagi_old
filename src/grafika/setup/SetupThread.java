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
		dati.initialize(); //z�m��ana var b�t atkar�ga no datiem
		
		threadName=dati.windowTitle;
		thread = new Thread(this, threadName);
		running = true;
		minimized = false;
		windowActive = true;
		
		grafika = new Grafika();
		grafika.initialize(this); //z�m��ana
		
		input = new Input();
		input.initialize(this); //nav j�z�m�, t�p�c p�c grafikas
		
		
		thread.start();
	}

	@Override
	public void run() {
		
		while (running) {
			
			//if (dati.k>=Dati.kmax) running=false; //izbeidz ciklu, kad k sasniedz kmax
			
			
			if (!minimized) {
				
				buttonActions();
				
				if (windowActive) {} //apr��ini
				
				grafika.main(this); //tikai z�m��ana, nek�di apr��ini
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
			
			dati.buttonList.get(i).actions(this); //p�rbauda katras pogas statusu
			
			if (dati.buttonList.get(i).result) { //ja poga nostr�d�jusi
				
				if (i==0) { //pirm� poga
					galvenais.Main.pauze=!galvenais.Main.pauze;
					
				} else if (i==1) { //otr� poga
					grafika.main.PlayerThread threadTemp=new grafika.main.PlayerThread();
					threadTemp.playerName=dataBase.Dati.cilvekiList.get(0).vards;
					threadTemp.start();
					
				} else if (i==2) { //tre�� poga
					dati.tablo1Draw=!dati.tablo1Draw;
					
				} else if (i==3) { //ceturt� poga
					dati.tablo2Draw=!dati.tablo2Draw;
					
				} else if (i==4) { //piekt� poga
					dati.miniMapDraw=!dati.miniMapDraw;
					
				} else if (i==5) { //sest� poga
					dati.inputPanelDraw=!dati.inputPanelDraw;
					
				} else if (i==6) { //sept�t� poga
					dati.colorPanelDraw=!dati.colorPanelDraw;
					
				} else if (i==7) { //astot� poga
					Parametri.goldGenRate+=0.002;
					Parametri.paikaGenRate+=0.005;
					
				} else if (i==8) { //dev�t� poga
					Parametri.goldGenRate-=0.002;
					Parametri.paikaGenRate-=0.005;
					
				}
				
				dati.buttonList.get(i).result=false; //reseto pogas statusu
			}
		}
	}
	
}

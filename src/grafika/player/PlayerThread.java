package grafika.player;

public class PlayerThread implements Runnable{
	
	private static Thread thread;
	private static String threadName;
	public boolean running, minimized, windowActive;
	
	protected Dati dati;
	protected Grafika grafika;
	protected Input input;
	
	public void initialize(String playerName) {
		dati = new Dati(); //zîmçðana var bût atkarîga no datiem
		dati.playerName = playerName;
		start();
	}
	
	public void start() {
		dati.initialize(this);
		dati.playerInitialize(this, true);
		
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
			
			//if (dati.k>=Dati.kmax) running=false; //izbeidz ciklu, kad k sasniedz kmax
			
			
			if (!minimized) {
				if (dati.findPlayer(this)<0) {
					dati.playerDead();
				}
				
				buttonActions();
				
				if (windowActive) {} //aprçíini
				
				grafika.main(this); //tikai zîmçðana, nekâdi aprçíini
			}
			
			dati.calculationTimeCalculator.time(true);
			try{
				Thread.sleep(dati.calculationTimeCalculator.sleepT());
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		grafika.ekrans.dispose();
	}
	
	private void buttonActions() {
		
		for (int i=0; i<dati.buttonList.size();i++) {
			
			dati.buttonList.get(i).actions(this); //pârbauda katras pogas statusu
			
			if (dati.buttonList.get(i).result) { //ja poga nostrâdâjusi
				
				if(i==0) { //pirmâ poga
					galvenais.Main.pauze=!galvenais.Main.pauze;
					
				} else if (i==1) { //otrâ poga
					dati.cilvekiDrawInfo=!dati.cilvekiDrawInfo;
					
				} else if (i==2) { //treðâ poga
					dati.cilvekiDrawR=!dati.cilvekiDrawR;
					
				} else if (i==3) { //ceturtâ poga
					dati.lietasDrawInfo=!dati.lietasDrawInfo;
					
				} else if (i==4) { //piektâ poga
					if (dati.playerDead) {
						dati.playerInitialize(this, false);
					}
					
				}
				
				dati.buttonList.get(i).result=false; //reseto nostrâdâjuðas pogas statusu
			}
		}
	}
	
	
	
}

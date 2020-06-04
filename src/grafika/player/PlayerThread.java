package grafika.player;

public class PlayerThread implements Runnable{
	
	private static Thread thread;
	private static String threadName;
	public boolean running, minimized, windowActive;
	
	protected Dati dati;
	protected Grafika grafika;
	protected Input input;
	
	public void initialize(String playerName) {
		dati = new Dati(); //z�m��ana var b�t atkar�ga no datiem
		dati.playerName = playerName;
		start();
	}
	
	public void start() {
		dati.initialize(this);
		dati.playerInitialize(this, true);
		
		int maxFrameRate=50; //kadri sekund�
		dati.calculationTimeCalculator.delayMin=1000/maxFrameRate; //simul�cijas solis
		
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
			dati.calculationTimeCalculator.time(true);
			
			//if (dati.k>=Dati.kmax) running=false; //izbeidz ciklu, kad k sasniedz kmax
			
			
			if (!minimized) {
				if (dati.findPlayer(this)<0) {
					dati.playerDead();
				}
				
				buttonActions();
				
				if (windowActive) {} //apr��ini
				
				grafika.main(this); //tikai z�m��ana, nek�di apr��ini
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
			
			dati.buttonList.get(i).actions(this); //p�rbauda katras pogas statusu
			
			if (dati.buttonList.get(i).result) { //ja poga nostr�d�jusi
				
				if(i==0) { //pirm� poga
					galvenais.Main.pauze=!galvenais.Main.pauze;
					
				} else if (i==1) { //otr� poga
					dati.cilvekiDrawInfo=!dati.cilvekiDrawInfo;
					
				} else if (i==2) { //tre�� poga
					dati.cilvekiDrawR=!dati.cilvekiDrawR;
					
				} else if (i==3) { //ceturt� poga
					dati.lietasDrawInfo=!dati.lietasDrawInfo;
					
				} else if (i==4) { //piekt� poga
					if (dati.playerDead) {
						dati.playerInitialize(this, false);
					}
					
				}
				
				dati.buttonList.get(i).result=false; //reseto nostr�d�ju�as pogas statusu
			}
		}
	}
	
	
	
}

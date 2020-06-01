package grafika.main;

public class PlayerThread implements Runnable{
	
	public String playerName;
	private static Thread thread;
	private static String threadName;
	public boolean running, minimized, windowActive;
	
	protected Dati dati;
	protected Grafika grafika;
	protected Input input;
	
	
	public void start() {
		dati = new Dati();
		dati.initialize(this); //zîmçðana var bût atkarîga no datiem
		
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
				
				if(i==0) { //pirmâ poga
					galvenais.Main.pauze=!galvenais.Main.pauze;
					
				} else if (i==1) { //otrâ poga
					dati.cilvekiDrawInfo=!dati.cilvekiDrawInfo;
					
				} else if (i==2) { //treðâ poga
					dati.cilvekiDrawR=!dati.cilvekiDrawR;
					
				} else if (i==3) { //ceturtâ poga
					dati.lietasDrawInfo=!dati.lietasDrawInfo;
					
				} else if (i==4) { //piektâ poga
					reset();
					
				}
				
				dati.buttonList.get(i).result=false; //reseto nostrâdâjuðas pogas statusu
			}
		}
	}
	
	private void reset() {
		playerName=dataBase.Dati.cilvekiList.get(0).vards;
	}
	
}

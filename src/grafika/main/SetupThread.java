package grafika.main;

import calculations.KonstantesUniversal;
import calculations.lietas.LietuPreseti;

public class SetupThread implements Runnable{
	private static Thread thread;
	private static String threadName;
	public boolean running, minimized, windowActive;
	
	public Dati dati;
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
				dati.update();
				
				userInput();
				
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

	private void userInput() {

		buttonActions(); //uz ekrâna redzamo pogu notikumi
		keyboardActions(); //keyboard nospiesto pogu notikumi

	}

	private void buttonActions() {
		
		for (int i=0; i<dati.buttonList.size();i++) {
			
			dati.buttonList.get(i).actions(this); //pârbauda katras pogas statusu
			
			if (dati.buttonList.get(i).result) { //ja poga nostrâdâjusi
				
				buttonActionsSpecific(i);
				
				dati.buttonList.get(i).result=false; //reseto pogas statusu
			}
		}
	}

	private void buttonActionsSpecific(int i){
		if (i==0) { //pirmâ poga
			calculations.Main.pauze=!calculations.Main.pauze;

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
			KonstantesUniversal.overallGenRate+=0.01;

		} else if (i==9) { //desmitâ poga
			KonstantesUniversal.overallGenRate-=0.01;

		}
	}

	private void keyboardActions(){
		//te var nolasît piespiestâs keyboard pogas notikumiem
		//piemçram ja pogas[i]==xx notiek kaut kas - piemçram main.cilvekuList.cilveks.darbibas.kautkas
		//	vai focusfind=player+1, zoom++ / zoom--

//		for (int i=0; i<input.pogas.length;i++) {
//
//			//dati.buttonList.get(i).actions(this); //pârbauda katras pogas statusu
//
//			if (result) { //ja poga nostrâdâjusi
//
//				keyboardActionsSpecific(i);
//
//				result=false; //reseto pogas statusu
//			}
//		}

	}
	
	
}

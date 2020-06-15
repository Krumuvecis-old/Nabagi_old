package grafika.main;

import calculations.KonstantesUniversal;
import calculations.lietas.LietuPreseti;

public class SetupThread implements Runnable{
	private static Thread thread;
	private static String threadName;
	public boolean running, minimized, windowActive;

	public LayoutDati layout;
	public Dati dati;
	protected Grafika grafika;
	protected Input input;
	
	
	public void start() {
		//inicializâcija

		layout = new LayoutDati();
		layout.initialize();
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

		System.out.println("Setup thread initialized - running.");
	}

	@Override
	public void run() {
		
		while (running) {
			dati.calculationTimeCalculator.time(true);

			if (!minimized) galvenaisCikls();
			
			dati.calculationTimeCalculator.time(false);
			try{
				Thread.sleep(dati.calculationTimeCalculator.sleepT());
			} catch (Exception e){
				e.printStackTrace();
			}
		}

		System.out.println("Setup thread finished");
		grafika.ekrans.dispose();
	}

	private void galvenaisCikls(){
		//ðis visu laiku atkârtojas, kad nav minimizçts

		userInput();
		dati.update();
		//ðeit jâpievieno ekrâna izmçru maiòas (resize) pârbaude

		if (windowActive) {} //vieta kaut kâdiem aprçíiniem (tikai aktîvajam window)

		grafika.main(this); //tikai zîmçðana, nekâdi aprçíini
	}

	private void userInput() {
		ButtonActions.main(this, dati.buttonList); //uz ekrâna redzamo pogu notikumi
		KeyboardActions.main(input.pogas); //keyboard nospiesto pogu notikumi
	}


}

package localClient;

import localClient.grafika.Button;
import localClient.grafika.Grafika;
import localClient.grafika.Input;

public class ClientThread implements Runnable{
	public CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();
	public boolean running, minimized, windowActive;

	public Dati dati;
	public Grafika grafika;
	public Input input;
	
	public ClientThread() {
		System.out.println("ClientThread: Starting initialization");
		running = true;
		minimized = false;
		windowActive = true;

		dati = new Dati();

		int maxFrameRate=50; //kadri sekundç
		calculationTimeCalculator.delayMin=1000/maxFrameRate; //simulâcijas solis

		grafika = new Grafika();
		grafika.initialize(this); //zîmçðana
		
		input = new Input(this); //nav jâzîmç, tâpçc pçc grafikas

		new Thread(this, "Setup thread").start();
		System.out.println("ClientThread: initialized - running.");
	}

	@Override
	public void run() {
		while (running) {
			calculationTimeCalculator.time(true);
			if (!minimized) galvenaisCikls();
			calculationTimeCalculator.time(false);
			try{
				Thread.sleep(calculationTimeCalculator.sleepT());
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("ClientThread: finished - closed.");
		grafika.ekrans.dispose();
	}

	private void galvenaisCikls(){
		//ðis visu laiku atkârtojas, kad nav minimizçts

		userInput();
		dati.update(grafika.grafika);

		if (windowActive) {} //vieta kaut kâdiem aprçíiniem (tikai aktîvajam window)

		grafika.main(this); //tikai zîmçðana, nekâdi aprçíini
	}

	private void userInput() {
		Button.checkButtonActions(this); //uz ekrâna redzamo pogu notikumi
		Input.keyboardActionCheck(input.pogas, this); //keyboard nospiesto pogu notikumi
	}


}

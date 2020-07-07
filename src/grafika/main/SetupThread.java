package grafika.main;

public class SetupThread implements Runnable{
	public boolean running, minimized, windowActive;

	public Dati dati;
	public Grafika grafika;
	public Input input;
	
	public SetupThread() {
		System.out.println("SetupThread: Starting initialization");
		running = true;
		minimized = false;
		windowActive = true;

		dati = new Dati();

		int maxFrameRate=50; //kadri sekundç
		dati.calculationTimeCalculator.delayMin=1000/maxFrameRate; //simulâcijas solis

		grafika = new Grafika();
		grafika.initialize(this); //zîmçðana
		
		input = new Input(this); //nav jâzîmç, tâpçc pçc grafikas

		new Thread(this, "Setup thread").start();
		System.out.println("SetupThread: initialized - running.");
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
		System.out.println("SetupThread: finished - closed.");
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
		KeyboardActions.main(input.pogas); //keyboard nospiesto pogu notikumi
	}


}

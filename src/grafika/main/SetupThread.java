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

		int maxFrameRate=50; //kadri sekund�
		dati.calculationTimeCalculator.delayMin=1000/maxFrameRate; //simul�cijas solis

		grafika = new Grafika();
		grafika.initialize(this); //z�m��ana
		
		input = new Input(this); //nav j�z�m�, t�p�c p�c grafikas

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
		//�is visu laiku atk�rtojas, kad nav minimiz�ts

		userInput();
		dati.update(grafika.grafika);

		if (windowActive) {} //vieta kaut k�diem apr��iniem (tikai akt�vajam window)

		grafika.main(this); //tikai z�m��ana, nek�di apr��ini
	}

	private void userInput() {
		Button.checkButtonActions(this); //uz ekr�na redzamo pogu notikumi
		KeyboardActions.main(input.pogas); //keyboard nospiesto pogu notikumi
	}


}

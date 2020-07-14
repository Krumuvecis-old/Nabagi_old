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

		int maxFrameRate=50; //kadri sekund�
		calculationTimeCalculator.delayMin=1000/maxFrameRate; //simul�cijas solis

		grafika = new Grafika();
		grafika.initialize(this); //z�m��ana
		
		input = new Input(this); //nav j�z�m�, t�p�c p�c grafikas

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
		//�is visu laiku atk�rtojas, kad nav minimiz�ts

		userInput();
		dati.update(grafika.grafika);

		if (windowActive) {} //vieta kaut k�diem apr��iniem (tikai akt�vajam window)

		grafika.main(this); //tikai z�m��ana, nek�di apr��ini
	}

	private void userInput() {
		Button.checkButtonActions(this); //uz ekr�na redzamo pogu notikumi
		Input.keyboardActionCheck(input.pogas, this); //keyboard nospiesto pogu notikumi
	}


}

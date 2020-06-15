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
		//inicializ�cija

		layout = new LayoutDati();
		layout.initialize();
		dati = new Dati();
		dati.initialize(); //z�m��ana var b�t atkar�ga no datiem
		
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
		//�is visu laiku atk�rtojas, kad nav minimiz�ts

		userInput();
		dati.update();
		//�eit j�pievieno ekr�na izm�ru mai�as (resize) p�rbaude

		if (windowActive) {} //vieta kaut k�diem apr��iniem (tikai akt�vajam window)

		grafika.main(this); //tikai z�m��ana, nek�di apr��ini
	}

	private void userInput() {
		ButtonActions.main(this, dati.buttonList); //uz ekr�na redzamo pogu notikumi
		KeyboardActions.main(input.pogas); //keyboard nospiesto pogu notikumi
	}


}

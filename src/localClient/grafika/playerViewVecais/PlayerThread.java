package localClient.grafika.playerViewVecais;

public class PlayerThread implements Runnable{
	
	private static Thread thread;
	private static String threadName;
	public boolean running, minimized, windowActive;
	
	public Dati dati;
	public Grafika grafika;
	public GrafikaSupplement grafika2;
	public Input input;
	
	public void initialize(String playerName) {
		dati = new Dati(); //z�m��ana var b�t atkar�ga no datiem
		dati.playerName = playerName;
		start();
	}
	
	public void start() {
		dati.initialize(this);
		//dati.playerInitialize(this, true);
		
		int maxFrameRate=50; //kadri sekund�
		dati.calculationTimeCalculator.delayMin=1000/maxFrameRate; //simul�cijas solis
		
		threadName=dati.windowTitle;
		thread = new Thread(this, threadName);
		running = true;
		minimized = false;
		windowActive = true;

		grafika2 = new GrafikaSupplement();
		grafika2.initialize(this);
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

			if (!minimized) galvenaisCikls(); //galvenais cikls, kad nav minimiz�ts
			
			dati.calculationTimeCalculator.time(true);
			try{
				Thread.sleep(dati.calculationTimeCalculator.sleepT());
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		grafika.ekrans.dispose();
	}

	private void galvenaisCikls(){
		//�is visu laiku atk�rtojas, kad nav minimiz�ts

//		if (dati.findPlayer()==null) { //ja neatrod sp�l�t�ju ar atbilsto�u v�rdu, tad miris
//			dati.playerDead=true;
//		}

		userInput();

		//�eit j�pievieno ekr�na izm�ru mai�as (resize) p�rbaude

		if (windowActive) {} //vieta kaut k�diem apr��iniem (tikai akt�vajam window)

		grafika.main(this); //tikai z�m��ana, nek�di apr��ini
	}

	private void userInput() {
		ButtonActions.main(this, dati.buttonList); //uz ekr�na redzamo pogu notikumi
		KeyboardActions.main(input.pogas); //keyboard nospiesto pogu notikumi
	}

}

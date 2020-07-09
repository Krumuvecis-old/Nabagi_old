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
		dati = new Dati(); //zîmçðana var bût atkarîga no datiem
		dati.playerName = playerName;
		start();
	}
	
	public void start() {
		dati.initialize(this);
		//dati.playerInitialize(this, true);
		
		int maxFrameRate=50; //kadri sekundç
		dati.calculationTimeCalculator.delayMin=1000/maxFrameRate; //simulâcijas solis
		
		threadName=dati.windowTitle;
		thread = new Thread(this, threadName);
		running = true;
		minimized = false;
		windowActive = true;

		grafika2 = new GrafikaSupplement();
		grafika2.initialize(this);
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

			if (!minimized) galvenaisCikls(); //galvenais cikls, kad nav minimizçts
			
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
		//ðis visu laiku atkârtojas, kad nav minimizçts

//		if (dati.findPlayer()==null) { //ja neatrod spçlçtâju ar atbilstoðu vârdu, tad miris
//			dati.playerDead=true;
//		}

		userInput();

		//ðeit jâpievieno ekrâna izmçru maiòas (resize) pârbaude

		if (windowActive) {} //vieta kaut kâdiem aprçíiniem (tikai aktîvajam window)

		grafika.main(this); //tikai zîmçðana, nekâdi aprçíini
	}

	private void userInput() {
		ButtonActions.main(this, dati.buttonList); //uz ekrâna redzamo pogu notikumi
		KeyboardActions.main(input.pogas); //keyboard nospiesto pogu notikumi
	}

}

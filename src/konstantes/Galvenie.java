package konstantes;

class Galvenie {
	private static String versija="0.10.5 (beta)";
	private static int maxFrameRate=50; //sekund�
	
	protected static void initialize() {
		Parametri.versija=versija;
		
		Parametri.simulationMaxDelay=1000/maxFrameRate; //simul�cijas solis (default bija 10, testam var lietot 1)
	}
	
}

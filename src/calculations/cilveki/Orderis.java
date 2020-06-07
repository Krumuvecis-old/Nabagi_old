package calculations.cilveki;

public class Orderis {
	public String prece;
	public boolean perk;
	public double cena;
	public double daudzums;

	public boolean checkStatus(){
		return daudzums > 0;
	}

	public void decreaseAmount(double delta){
		daudzums-=delta;
	}



}

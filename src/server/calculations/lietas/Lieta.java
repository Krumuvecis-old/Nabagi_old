package server.calculations.lietas;

import server.dataBase.DataBase;

import java.util.List;

public class Lieta {

	public double x, y;
	public String tips;
	public double daudzums;
	public double condition;

	public Lieta(String _tips, double _daudzums, double _x, double _y){
		double defaultCondition = 1;

		x = _x;
		y = _y;

		if (LietuTips.lietuTipi.containsKey(_tips)) tips = _tips;
		else tips = "Default";

		daudzums = _daudzums;
		condition = defaultCondition;
	}

	public void drop(List<Integer> chunkXY){
		DataBase.laukums.get(chunkXY).lietas.add(this);
	}

}
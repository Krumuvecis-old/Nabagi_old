package server.calculations.cilveki;

import java.util.ArrayList;
import java.util.List;

public class Koord {
	//objektu kustîbas atviegloðanai - pamatparametri

	public double x, y, v, fi;
	public List<Integer> chunkXY;

	public Koord(double _x, double _y, double _v, double _fi, List<Integer> _chunkXY){
		x = _x;
		y = _y;
		v = _v;
		fi = _fi;
		chunkXY = new ArrayList<>();
		chunkXY.add(_chunkXY.get(0));
		chunkXY.add(_chunkXY.get(1));
	}
}

package calculations.cilveki;

public class Koord {
	//objektu kustîbas atviegloðanai - pamatparametri

	public double x, y, v, fi;
	public int[] chunkXY;

	public Koord(double _x, double _y, double _v, double _fi, int[] _chunkXY){
		x = _x;
		y = _y;
		v = _v;
		fi = _fi;
		chunkXY = new int[]{_chunkXY[0], _chunkXY[1]};
	}
}

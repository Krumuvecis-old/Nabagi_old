package localClient.grafika.playerViewVecais.centerPanel.map;

public class Map {

//    private ArrayList<Komanda> komandasList = server.calculations.CalculationsThread.komandasList;
//    private ArrayList<ArrayList<server.calculations.MapChunk>> laukums = server.calculations.CalculationsThread.laukums;
//
//    private Terrain drawTerrain = new Terrain();
//    private Loot drawLoot = new Loot();
//    private Cilveki drawCilveki = new Cilveki();
//
//    private int nobideX, nobideY, kartePlatums;
//
//    private double merogs, R1, R2, x0, y0; //R1 - ârçjais, R2- iekðçjais
//
//    public void main(Graphics g, PlayerThread thread) {
//        nobideX = Dati.karteNobideX;
//        nobideY = Dati.karteNobideY;
//        kartePlatums = thread.dati.kartePlatums;
//
//
//        if(!thread.dati.playerDead) {//atjaunina parametrus tikai, kad dzîvs - kad miris râdîs, to vietu, kur nomira
//            Cilveks cilveks = thread.dati.findPlayer();
//
//            R1 = cilveks.R1; //tâlais
//            R2 = cilveks.R2; //tuvais
//            merogs = thread.dati.kartePlatums/(R1*2);
//
//            x0 = cilveks.xyz.x;
//            y0 = cilveks.xyz.y;
//        } else playerDead(g);
//
//        drawTerrain.main(g, thread, nobideX, nobideY, merogs);
//        //drawLoot.main(g, thread.dati.lietasDrawInfo); // g,drawInfo
//        drawCilveki.main(g);
//
//    }
//
//    private void playerDead(Graphics g) {
//        g.setColor(Color.red);
//
//        double[] koord = getAbsoluteCoordinates(true, x0, y0);
//        int correctionX = -34,
//                correctionY = -5;
//        koord[0] += correctionX;
//        koord[1] += correctionY;
//
//        g.drawString("GAME OVER", (int)koord[0], (int)koord[1]);
//    }
//
//    protected double[] getAbsoluteCoordinates(boolean center, double dx, double dy) { //kartes elementu absolûtâs koordinâtas ekrânâ
//
//        double x, y, korekcijaX=0, korekcijaY=0;
//
//        if (!center) { //tiem, kas nav centrâ, pienâkas koordinâtu korekcija
//            korekcijaX = dx * merogs;
//            korekcijaY = dy * merogs;
//        }
//
//        x = nobideX + kartePlatums/2.0 + korekcijaX;
//        y = nobideY + kartePlatums/2.0 + korekcijaY;
//
//        return new double[] {x,y};
//    }

}

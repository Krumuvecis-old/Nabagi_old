package grafika.player.centerPanel.map;

import calculations.KonstantesUniversal;
import calculations.cilveki.Cilveks;
import calculations.komandas.Komanda;
import calculations.konstantes.Fizikas;
import calculations.konstantes.Formulas;
import calculations.konstantes.Grafiskie;
import calculations.lietas.Lieta;
import grafika.KonstantesGrafikai;
import grafika.player.Dati;
import grafika.player.PlayerThread;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private PlayerThread thread;

    private ArrayList<Komanda> komandasList = calculations.Main.komandasList;
    private ArrayList<ArrayList<calculations.MapChunk>> laukums = calculations.Main.laukums;

    private Terrain drawTerrain = new Terrain();
    private Loot drawLoot = new Loot();
    private Cilveki drawCilveki = new Cilveki();

    private int nobideX, nobideY;
    @SuppressWarnings("unused")
    private double merogs, R1, R2, x0, y0; //R1 - �r�jais, R2- iek��jais

    public void main(Graphics g, PlayerThread threadTemp) {
        thread = threadTemp;
        nobideX = Dati.karteNobideX;
        nobideY = Dati.karteNobideY;

        if(!thread.dati.playerDead) {
            R1=thread.dati.player.R1; //t�lais
            R2=thread.dati.player.R2; //tuvais
            merogs=thread.dati.kartePlatums/(R1*2);
        }

        if(!thread.dati.playerDead) { //atjaunina koordin�tas tikai, kad dz�vs - kad miris r�d�s, to vietu, kur nomira
            x0=thread.dati.player.xyz.x;
            y0=thread.dati.player.xyz.y;
        } else playerDead(g);

        drawTerrain.main(g, thread, nobideX, nobideY, merogs);
        //drawLoot.main(g, thread.dati.lietasDrawInfo); // g,drawInfo
        drawCilveki.main(g);

    }

    private void playerDead(Graphics g) {
        g.setColor(Color.red);

        double[] koord = getAbsoluteCoordinates(true, x0, y0);
        int correctionX = -34,
                correctionY = -5;
        koord[0] += correctionX;
        koord[1] += correctionY;

        g.drawString("GAME OVER", (int)koord[0], (int)koord[1]);
    }

    protected double[] getAbsoluteCoordinates(boolean center, double dx, double dy) { //kartes elementu absol�t�s koordin�tas ekr�n�

        double x, y, korekcijaX=0, korekcijaY=0;

        if (!center) { //tiem, kas nav centr�, pien�kas koordin�tu korekcija
            korekcijaX = dx * merogs;
            korekcijaY = dy * merogs;
        }

        x=nobideX + thread.dati.kartePlatums/2+korekcijaX;
        y=nobideY + thread.dati.kartePlatums/2+korekcijaY;

        return new double[] {x,y};
    }

}

package grafika.main.map;

import calculations.MapChunk;
import calculations.cilveki.Cilveks;
import calculations.komandas.Komanda;
import calculations.konstantes.Fizikas;
import calculations.konstantes.Formulas;
import grafika.KonstantesGrafikai;
import grafika.main.SetupThread;

import java.awt.*;
import java.util.ArrayList;

class Cilveki {

    protected static void main(Graphics g, SetupThread thread, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums, ArrayList<Komanda> komandasList) { //papildinâjums kartei


        double resnumaKoefic = Fizikas.resnumaKoefic;
        for(int i=0;i<thread.dati.cilvekuPilnaisList.size();i++) {

            Cilveks cilveks = Cilveks.getPlayer(thread.dati.cilvekuPilnaisList.get(i)); //pats apskatâmais spçlçtâjs

            double resnums=resnumaKoefic*cilveks.hpmax*merogs;


            int komanda=0; //pçc  komandas nosaka krâsu
            for (int j=0;j<komandasList.size();j++) {
                if(cilveks.komanda==komandasList.get(j).nosaukums) {
                    komanda=j;
                    break;
                }
            }

            double hpRatio=cilveks.hp/cilveks.hpmax;

            Color krasa = new Color(Color.HSBtoRGB( (float)Formulas.getHue(komandasList.get(komanda).krasa),
                    (float)KonstantesGrafikai.cilvekiKrasaSaturation,
                    (float)(KonstantesGrafikai.cilvekiKrasaBrightnessMin + hpRatio *
                            (KonstantesGrafikai.cilvekiKrasaBrightnessMax - KonstantesGrafikai.cilvekiKrasaBrightnessMin)) ));

            //rumpis

            double x=x0+cilveks.xyz.x*merogs, y=y0+cilveks.xyz.y*merogs;

            g.setColor(krasa); //iekða
            g.fillOval((int)(x-resnums/2),
                    (int)(y-resnums/2),
                    (int)resnums, (int)resnums);
            g.setColor(Color.black);//kontûra
            g.drawOval((int)(x-resnums/2),
                    (int)(y-resnums/2),
                    (int)resnums, (int)resnums);

            if(cilveks.vards==komandasList.get(komanda).galvenais) { //karalis
                g.setColor(new Color(0,0,0)); //kroòa krâsa - melns punkts
                double kronaResnums=resnums/2;
                g.fillOval((int)(x-kronaResnums/2),
                        (int)(y-kronaResnums/2),
                        (int)kronaResnums, (int)kronaResnums); //kronis
            }


            if(i==thread.dati.playerFocusNumber) { //fokusçtâ spçlçtâja redzesloks
                g.setColor(krasa);
                double R2temp=cilveks.R2*merogs;
                g.drawOval((int)(x-R2temp), (int)(y-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - tâlais
            }

        }

    }

}

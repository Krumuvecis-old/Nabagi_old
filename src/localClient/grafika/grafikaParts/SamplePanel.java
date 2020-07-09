package localClient.grafika.grafikaParts;

import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public abstract class SamplePanel {

    public int[] XY, //paneļa sākumpunkts ekrānā
            size; //paneļa izmēri sampleLayout pielietošanas aprēķiniem
    Color[] colors;

    public ArrayList<Button> buttonList;

    public SamplePanel(int[] panelXY, int[] panelSize, Color[] colorPair){
        updateValues(panelXY, panelSize, colorPair);
    }

    private void updateValues(int[] panelXY, int[] panelSize, Color[] colorPair){
        XY = panelXY;
        size = panelSize;
        colors = new Color[]{colorPair[0], colorPair[1]};
    }

    public void draw(Graphics g, int[] panelXY, int[] panelSize, Color[] colorPair){
        updateValues(panelXY, panelSize, colorPair);
        drawFons(g);
    }

    private void drawFons(Graphics g){
        g.setColor(colors[0]);
        g.fillRect(XY[0], XY[1], size[0], size[1]);
    }

}

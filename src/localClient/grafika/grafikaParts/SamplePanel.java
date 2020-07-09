package localClient.grafika.grafikaParts;

import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public abstract class SamplePanel {

    public int[] XY, //paneļa sākumpunkts ekrānā
            size; //paneļa izmēri sampleLayout pielietošanas aprēķiniem
    Color backgroundColor;

    public ArrayList<Button> buttonList;

    public SamplePanel(int[] panelXY, int[] panelSize, Color background){
        updateLayout(panelXY, panelSize);
        backgroundColor = background;
    }

    private void updateLayout(int[] panelXY, int[] panelSize){
        XY = panelXY;
        size = panelSize;
    }

    public void draw(Graphics g, int[] panelXY, int[] panelSize){
        updateLayout(panelXY, panelSize);
        drawFons(g);
    }

    private void drawFons(Graphics g){
        g.setColor(backgroundColor);
        g.fillRect(XY[0], XY[1], size[0], size[1]);
    }

}

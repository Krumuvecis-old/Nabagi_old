package localClient.grafika.grafikaParts;

import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public class SamplePanel {

    public int[] XY, //paneļa sākumpunkts ekrānā
            size; //paneļa izmēri layout pielietošanas aprēķiniem

    public ArrayList<Button> buttonList;

    void update(int[] panelXY, int[] panelSize){
        XY = panelXY;
        size = panelSize;
    }

    void drawFons(Graphics g, Color background, Color contour){
        g.setColor(background);
        g.fillRect(XY[0], XY[1], size[0], size[1]);
        g.setColor(contour);
        g.drawRect(XY[0], XY[1], size[0], size[1]);
    }

}

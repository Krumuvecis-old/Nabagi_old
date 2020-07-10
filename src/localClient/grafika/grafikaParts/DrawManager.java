package localClient.grafika.grafikaParts;

import localClient.ClientThread;
import localClient.ColorPalette;

import java.awt.*;

public abstract class DrawManager {

    public SampleLayout layout;
    public InputActions inputActions;

    public Header header;
    public Footer footer;
    public PanelL panelL;
    public PanelR panelR;
    public CenterPanel centerPanel;

    public boolean drawHeader = true, drawFooter = true,
            drawPanelL = true,  drawPanelR = true,
            drawCenterPanel = true,
            drawOverPanel = true;

    public DrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette){
        layout = new SampleLayout(ekranaPlatums, ekranaAugstums);
        inputActions = new InputActions();

        header = new Header(layout, colorPalette.pair1);
        footer = new Footer(layout, colorPalette.pair1);
        panelL = new PanelL(layout, colorPalette.pair2);
        panelR = new PanelR(layout, colorPalette.pair2);
        centerPanel = new CenterPanel(layout, colorPalette.pair3);
    }

    public void main(Graphics g, ClientThread thread){
        drawFons(g, thread);

        if(drawCenterPanel) centerPanel.draw(g, thread.dati, layout, true);

        if(drawPanelL) panelL.draw(g, thread, layout, true);
        if(drawPanelR) panelR.draw(g, thread.dati, layout);

        if(drawHeader) header.draw(g, thread.dati, layout);
        if(drawFooter) footer.draw(g, thread.dati, layout);

        if(thread.dati.grafikasDati.drawLayoutGrid) drawLayoutGrid(g, thread.dati.grafikasDati.layoutGridColor);

        if(drawOverPanel) drawOverPanel();

    }

    private void drawFons(Graphics g, ClientThread thread) {
        g.setColor(thread.dati.grafikasDati.backgroundColor);
        g.fillRect(0, 0, thread.grafika.grafika.getWidth(), thread.grafika.grafika.getHeight());
    }

    private void drawLayoutGrid(Graphics g, Color gridColor){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0, layout.ekranaPlatums, layout.ekranaAugstums);


        g.setColor(gridColor);
        int[] textOffset = {5,15}, textXY;

        g.drawRect(header.XY[0], header.XY[1], header.size[0], header.size[1]); //header box
        textXY = new int[]{header.XY[0] + textOffset[0], header.XY[1] + textOffset[1]};
        g.drawString("LobbyHeader", textXY[0], textXY[1]);

        g.drawRect(panelL.XY[0], panelL.XY[1], panelL.size[0], panelL.size[1]); //panelL box
        textXY = new int[]{panelL.XY[0] + textOffset[0], panelL.XY[1] + textOffset[1]};
        g.drawString("Left panel", textXY[0], textXY[1]);

        g.drawRect(panelR.XY[0], panelR.XY[1], panelR.size[0], panelR.size[1]); //panelR box
        textXY = new int[]{panelR.XY[0] + textOffset[0], panelR.XY[1] + textOffset[1]};
        g.drawString("Right panel", textXY[0], textXY[1]);

        g.drawRect(centerPanel.XY[0], centerPanel.XY[1], centerPanel.size[0], centerPanel.size[1]); //centerPanel box
        textXY = new int[]{centerPanel.XY[0] + textOffset[0], centerPanel.XY[1] + textOffset[1]};
        g.drawString("Center panel", textXY[0], textXY[1]);
        g.drawRect(layout.centerPanelContentsX, layout.centerPanelContentsY,
                layout.centerPanelContentsWX, layout.centerPanelContentsWY); //centerPanelContents box

        g.drawRect(footer.XY[0], footer.XY[1], footer.size[0], footer.size[1]); //footer box
        textXY = new int[]{footer.XY[0] + textOffset[0], footer.XY[1] + textOffset[1]};
        g.drawString("PlayerViewFooter", textXY[0], textXY[1]);

    }

    private void drawOverPanel(){
        //overPanel not implemented yet
    }

}

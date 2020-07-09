package localClient.grafika.grafikaParts;

import localClient.ClientThread;

import java.awt.*;

public abstract class DrawManager {

    public SampleLayout layout;

    public Header header;
    public Footer footer;
    public PanelL panelL;
    public PanelR panelR;
    public CenterPanel centerPanel;

    public boolean drawHeader = true, drawFooter = true,
            drawPanelL = true,  drawPanelR = true,
            drawCenterPanel = true,
            drawOverPanel = true;

    public DrawManager(int ekranaPlatums, int ekranaAugstums){
        layout = new SampleLayout(ekranaPlatums, ekranaAugstums);

        header = new Header(layout);
        footer = new Footer(layout);
        panelL = new PanelL(layout);
        panelR = new PanelR(layout);
        centerPanel = new CenterPanel(layout);
    }

    public void main(Graphics g, ClientThread thread){
        drawFons(g, thread);

        if(drawCenterPanel) centerPanel.draw(g, thread.dati, layout, true, true);

        if(drawPanelL) panelL.draw(g, thread.dati, layout, thread.dati.grafikasDati.colorPalette, true, true, thread);
        if(drawPanelR) panelR.draw(g, thread.dati, layout, true);

        if(drawHeader) header.draw(g, thread.dati, layout);
        if(drawFooter) footer.draw(g, thread.dati, layout, true);

        if(thread.dati.grafikasDati.drawLayoutGrid) drawLayoutGrid(g, thread.dati.grafikasDati.layoutGridColor);

        if(drawOverPanel) drawOverPanel();

    }

    private void drawFons(Graphics g, ClientThread thread) {
        g.setColor(thread.dati.grafikasDati.backgroundColor);
        g.fillRect(0, 0, thread.grafika.grafika.getWidth(), thread.grafika.grafika.getHeight());
    }

    private void drawLayoutGrid(Graphics g, Color gridColor){
        g.setColor(gridColor);

        g.drawLine(layout.panelLX, layout.headerY,
                layout.ekranaPlatums - layout.panelROffset, layout.headerY); //horizontal divider 0-header
        g.drawLine(layout.panelLX, layout.panelY,
                layout.ekranaPlatums - layout.panelROffset, layout.panelY); //horizontal divider header-panel
        g.drawLine(layout.panelLX, layout.footerY,
                layout.ekranaPlatums - layout.panelROffset, layout.footerY); //horizontal divider panel-footer

        g.drawLine(layout.panelLX, layout.panelY,
                layout.panelLX, layout.footerY); //vertical divider 0-panelL
        g.drawLine(layout.centerPanelX, layout.panelY,
                layout.centerPanelX, layout.footerY); //vertical divider panelL-centerPanel
        g.drawLine(layout.panelRX, layout.panelY,
                layout.panelRX, layout.footerY); //vertical divider centerPanel-panelR
        g.drawLine(layout.ekranaPlatums - layout.panelROffset, layout.panelY,
                layout.ekranaPlatums - layout.panelROffset, layout.footerY); //vertical divider panelR-end

        g.drawRect(layout.centerPanelContentsX, layout.centerPanelContentsY,
                layout.centerPanelContentsWX, layout.centerPanelContentsWY); //border for centerPanel contents
    }

    private void drawOverPanel(){
        //overPanel not implemented yet
    }

}

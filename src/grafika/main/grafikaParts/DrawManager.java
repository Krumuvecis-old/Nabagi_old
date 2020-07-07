package grafika.main.grafikaParts;

import server.userInterface.Layout;
import server.userInterface.ServerUIThread;

import java.awt.*;

public class DrawManager {

    public Header header;
    public Footer footer;
    public Panel1 panel1;
    public Panel2 panel2;
    public Panel3 panel3;

    public DrawManager(Layout layout){
        header = new Header(layout);
        footer = new Footer(layout);
        panel1 = new Panel1(layout);
        panel2 = new Panel2(layout);
        panel3 = new Panel3(layout);
    }

    public void main(Graphics g, ServerUIThread thread){
        drawFons(g, thread);

        if(thread.dati.drawPanel3) panel3.draw(g, thread.dati);

        if(thread.dati.drawPanel1) panel1.draw(g, thread);
        if(thread.dati.drawPanel2) panel2.draw(g, thread.dati);

        if(thread.dati.drawHeader) header.draw(g, thread.dati);
        if(thread.dati.drawFooter) footer.draw(g, thread.dati);

        if(thread.dati.drawLayoutGrid) drawLayoutGrid(g, thread.dati.layout, thread.dati.layoutGridColor);

    }

    private void drawFons(Graphics g, ServerUIThread thread) {
        g.setColor(thread.dati.backgroundColor);
        g.fillRect(0, 0, thread.grafika.grafika.getWidth(), thread.grafika.grafika.getHeight());
    }

    private void drawLayoutGrid(Graphics g, Layout layout, Color gridColor){
        g.setColor(gridColor);

        g.drawLine(0, layout.headerY, layout.ekranaPlatums, layout.headerY); //vertical divider 0-header
        g.drawLine(0, layout.panelY, layout.ekranaPlatums, layout.panelY); //vertical divider header-panel
        g.drawLine(0, layout.footerY, layout.ekranaPlatums, layout.footerY); //vertical divider panel-footer
        g.drawLine(layout.panel1X, layout.panelY, layout.panel1X, layout.footerY); //horizontal divider 0-panel1
        g.drawLine(layout.panel2X, layout.panelY, layout.panel2X, layout.footerY); //horizontal divider panel1-panel2
        g.drawLine(layout.panel3X, layout.panelY, layout.panel3X, layout.footerY); //horizontal divider panel2-panel3
        g.drawLine(layout.ekranaPlatums - layout.panel3RightBorder, layout.panelY, layout.ekranaPlatums - layout.panel3RightBorder, layout.footerY); //horizontal divider panel3-end

        g.drawRect(layout.panel3ContentsX, layout.panel3ContentsY, layout.panel3contentsWX, layout.panel3contentsWY); //border for contents of panel3
    }


}

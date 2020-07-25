package localClient.grafika.grafikaParts;

import localClient.ClientThread;
import localClient.ColorPalette;
import server.calculations.laukums.Laukums;
import server.dataBase.DataBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class DrawManager {

    public SampleLayout layout;
    public InputActions inputActions;

    public Header header;
    public Footer footer;
    public PanelL panelL;
    public PanelR panelR;
    public CenterPanel centerPanel;
    public OverPanel overPanel;

    public boolean drawHeader = true, drawFooter = true,
            drawPanelL = true,  drawPanelR = true,
            drawCenterPanel = true,
            drawOverPanel = true;

    public static class DevelopTabloInfo {
        public enum TabloMode {
            none,
            tablo1, // kopçjais cilvçku info
            tablo2, // komandu info
            tablo3 // nâkotnei un testiem
        }

        public TabloMode tabloCurrent = TabloMode.none; //default
    }

    public DevelopTabloInfo developTabloInfo = new DevelopTabloInfo();

    public static class SpectateMapInfo {
        public static final double minZoom = 0.9;
        public double zoomFactor; //1 - râda 100%, 2 - râda 50%, utt
        public int[] centerXY; //skata fokuss
        public double merogsMin, merogs, chunkSizeGraphical;

        public boolean playerSelected, playerDead;
        public String selectedPlayerName;

        public boolean chunkGrid = false, cellGrid = false, mapWrap = false,
                drawPlayerInfo = false, drawRedzesloki = false,
                drawLootInfo = true;

        public SpectateMapInfo(){
            zoomFactor = minZoom;
            centerXY = new int[]{
                    Laukums.laukumaPlatumsSum / 2,
                    Laukums.laukumaAugstumsSum / 2};
            playerSelected = false;
            playerDead = false;
        }

        public void update(int[] contentsSize){
            updateMerogs(contentsSize);
            updatePlayerXY();
        }

        private void updateMerogs(int[] contentsSize){
            merogsMin = Math.min(
                    1.0 * contentsSize[0] / Laukums.laukumaPlatumsSum,
                    1.0 * contentsSize[1] / Laukums.laukumaAugstumsSum);

            merogs = merogsMin * zoomFactor;

            chunkSizeGraphical = Laukums.mapChunkW * merogs;
        }

        private void updatePlayerXY(){
            if(playerSelected){
                if(DataBase.cilvekuList.containsKey(selectedPlayerName)){
                    centerXY = new int[]{
                            (int)(DataBase.cilvekuList.get(selectedPlayerName).xyz.x +
                                    DataBase.cilvekuList.get(selectedPlayerName).xyz.chunkXY.get(0) * Laukums.mapChunkW),
                            (int)(DataBase.cilvekuList.get(selectedPlayerName).xyz.y +
                                    DataBase.cilvekuList.get(selectedPlayerName).xyz.chunkXY.get(1) * Laukums.mapChunkW)};
                } else {
                    playerDead = true;
                }
            }
        }

        public void selectPlayer(boolean select, String name){
            if(select){
                if(DataBase.cilvekuList.containsKey(name)){
                    playerSelected = true;
                    selectedPlayerName = name;
                    playerDead = false;
                }
            } else { //deselect
                playerSelected = false;
            }
        }

        public String pickRandomPlayer(){
            ArrayList<String> playerNames = new ArrayList<>(DataBase.cilvekuList.keySet());
            return playerNames.get((new Random()).nextInt(playerNames.size()));
        }

    }

    public SpectateMapInfo spectateMapInfo = new SpectateMapInfo();

    public DrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette){
        layout = new SampleLayout(ekranaPlatums, ekranaAugstums);
        inputActions = new InputActions();

        header = new Header(layout, colorPalette.pair1);
        footer = new Footer(layout, colorPalette.pair1);
        panelL = new PanelL(layout, colorPalette.pair2);
        panelR = new PanelR(layout, colorPalette.pair2);
        centerPanel = new CenterPanel(layout, colorPalette.pair3);
        overPanel = new OverPanel();
    }

    public void main(Graphics g, ClientThread thread){
        drawFons(g, thread);
        panelDrawingOrder(g, thread);
        if(thread.dati.grafikasDati.drawLayoutGrid) drawLayoutGrid(g, thread.dati.grafikasDati.layoutGridColor);
        if(drawOverPanel) overPanel.draw(g, thread, layout);
    }

    private void drawFons(Graphics g, ClientThread thread) {
        g.setColor(thread.dati.grafikasDati.backgroundColor);
        g.fillRect(0, 0, thread.grafika.grafika.getWidth(), thread.grafika.grafika.getHeight());
    }

    private void panelDrawingOrder(Graphics g, ClientThread thread){
        if(drawCenterPanel) {
            centerPanel.draw(g, thread.dati, layout);
            centerPanel.drawContentsBorder(g, layout, thread.dati.grafikasDati.colorPalette);
        }

        if(drawPanelL) panelL.draw(g, thread, layout);
        if(drawPanelR) panelR.draw(g, thread.dati, layout);

        if(drawHeader) header.draw(g, thread.dati, layout);
        if(drawFooter) footer.draw(g, thread.dati, layout);


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

}

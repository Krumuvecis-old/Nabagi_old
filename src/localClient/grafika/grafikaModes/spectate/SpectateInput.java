package localClient.grafika.grafikaModes.spectate;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import localClient.grafika.grafikaParts.InputActions;
import localClient.grafika.grafikaParts.SampleLayout;
import server.calculations.CalculationsThread;
import server.dataBase.DataBase;

import java.util.Comparator;
import java.util.Random;

public class SpectateInput extends InputActions {

    @Override
    public void keyboardActions(int numurs, ClientThread thread){
        DrawManager.SpectateMapInfo spectateMapInfo =
                thread.dati.drawManagerList.get(thread.dati.modeCurrent).spectateMapInfo;

        double viewChangeFactor = 0.018; //kustîbas âtrums pa karti ar keyboard pogâm
        int viewChangeRate = (int)Math.max(
                1,
                Math.min(DataBase.laukumaPlatumsSum, DataBase.laukumaAugstumsSum) / spectateMapInfo.zoomFactor * viewChangeFactor);

        if (!spectateMapInfo.playerSelected) {
            switch (numurs) { //numurs - klaviatûrâ nospiestâs pogas numurs
                case 87, 38 -> spectateMapInfo.centerXY[1] -= viewChangeRate; //W, up
                case 65, 37 -> spectateMapInfo.centerXY[0] -= viewChangeRate; //A, left
                case 83, 40 -> spectateMapInfo.centerXY[1] += viewChangeRate; //S, down
                case 68, 39 -> spectateMapInfo.centerXY[0] += viewChangeRate; //D, right

                default -> super.keyboardActions(numurs, thread);
            }
            normalizeXY(spectateMapInfo);

        } else {
            super.keyboardActions(numurs, thread);
        }

    }

    private void normalizeXY(DrawManager.SpectateMapInfo spectateMapInfo){
        if(spectateMapInfo.mapWrap){ //wrapping map
            if(spectateMapInfo.centerXY[0] < 0)
                spectateMapInfo.centerXY[0] += DataBase.laukumaPlatumsSum; //west
            else if(spectateMapInfo.centerXY[0] >= DataBase.laukumaPlatumsSum)
                spectateMapInfo.centerXY[0] -= DataBase.laukumaPlatumsSum; //east

            if(spectateMapInfo.centerXY[1] < 0)
                spectateMapInfo.centerXY[1] += DataBase.laukumaAugstumsSum; //north
            else if(spectateMapInfo.centerXY[1] >= DataBase.laukumaAugstumsSum)
                spectateMapInfo.centerXY[1] -= DataBase.laukumaAugstumsSum; //south
        } else{ //limited map
            if(spectateMapInfo.centerXY[0] < 0)
                spectateMapInfo.centerXY[0] = 0; //west
            else if(spectateMapInfo.centerXY[0] >= DataBase.laukumaPlatumsSum)
                spectateMapInfo.centerXY[0] = DataBase.laukumaPlatumsSum - 1; //east

            if(spectateMapInfo.centerXY[1] < 0)
                spectateMapInfo.centerXY[1] = 0; //north
            else if(spectateMapInfo.centerXY[1] >= DataBase.laukumaAugstumsSum)
                spectateMapInfo.centerXY[1] = DataBase.laukumaAugstumsSum - 1; //south
        }
    }

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.lobby;

            default -> super.headerButtonActions(reference, thread);
        }
    }

    @Override
    public void footerButtonActions(int reference, ClientThread thread){

        if (reference == 1) { //ja vairâk pogas, var likt switch -> case
            CalculationsThread.pauze = !CalculationsThread.pauze;
        } else {
            super.footerButtonActions(reference, thread);
        }
    }

    @Override
    public void leftButtonActions(int reference, ClientThread thread){
        DrawManager.SpectateMapInfo spectateMapInfo =
                thread.dati.drawManagerList.get(thread.dati.modeCurrent).spectateMapInfo;

        switch (reference) {
            case 1 -> spectateMapInfo.selectPlayer(false, ""); //deselect player
            case 2 -> { //select random player
                spectateMapInfo.selectPlayer(true, spectateMapInfo.pickRandomPlayer());
                resetZoom(thread, spectateMapInfo);
            }
            case 3 -> spectateMapInfo.chunkGrid = !spectateMapInfo.chunkGrid;
            case 4 -> spectateMapInfo.cellGrid = !spectateMapInfo.cellGrid;
            case 5 -> spectateMapInfo.mapWrap = !spectateMapInfo.mapWrap;
            case 6 -> spectateMapInfo.drawPlayerInfo = !spectateMapInfo.drawPlayerInfo;
            case 7 -> spectateMapInfo.drawRedzesloki = !spectateMapInfo.drawRedzesloki;
            case 8 -> spectateMapInfo.drawLootInfo = !spectateMapInfo.drawLootInfo;

            default -> super.leftButtonActions(reference, thread);
        }
    }

    @Override
    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("Spectate mode right panel button 1");
            case 2 -> System.out.println("Spectate mode right panel button 2");
            case 3 -> System.out.println("Spectate mode right panel button 3");

            default -> super.rightButtonActions(reference, thread);
        }
    }

    private static final double zoomChangeRate = 0.2;

    @Override
    public void centerButtonActions(int reference, ClientThread thread){

        DrawManager.SpectateMapInfo spectateMapInfo =
                thread.dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo;

        switch (reference) {
            case 1 -> zoomIn(spectateMapInfo);
            case 2 -> zoomOut(spectateMapInfo);
            case 3 -> resetZoom(thread, spectateMapInfo);

            default -> super.centerButtonActions(reference, thread);
        }
    }

    private void zoomIn(DrawManager.SpectateMapInfo spectateMapInfo){
        spectateMapInfo.zoomFactor += zoomChangeRate * spectateMapInfo.zoomFactor;
    }

    private void zoomOut(DrawManager.SpectateMapInfo spectateMapInfo){
        spectateMapInfo.zoomFactor -= zoomChangeRate * spectateMapInfo.zoomFactor;

        if (spectateMapInfo.zoomFactor < DrawManager.SpectateMapInfo.minZoom)
            resetZoomToMax(spectateMapInfo);
    }

    private void resetZoom(ClientThread thread, DrawManager.SpectateMapInfo spectateMapInfo){
        if(spectateMapInfo.playerSelected){
            SampleLayout layout = thread.dati.drawManagerList.get(Dati.ModeOption.spectate).layout;
            double R1 = DataBase.cilvekuList.get(spectateMapInfo.selectedPlayerName).R1;
            spectateMapInfo.zoomFactor =
                    Math.min(layout.centerPanelContentsWX, layout.centerPanelContentsWY)
                            / (R1 * 2 * spectateMapInfo.merogsMin);

        } else resetZoomToMax(spectateMapInfo);
    }

    private void resetZoomToMax(DrawManager.SpectateMapInfo spectateMapInfo){
        spectateMapInfo.zoomFactor = DrawManager.SpectateMapInfo.minZoom;
    }
}

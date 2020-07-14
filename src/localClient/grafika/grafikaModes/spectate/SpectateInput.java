package localClient.grafika.grafikaModes.spectate;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import localClient.grafika.grafikaParts.InputActions;
import server.calculations.CalculationsThread;
import server.dataBase.DataBase;

import java.util.Random;

public class SpectateInput extends InputActions {

    @Override
    public void keyboardActions(int numurs, ClientThread thread){
        DrawManager.SpectateMapInfo spectateMapInfo =
                thread.dati.drawManagerList.get(thread.dati.modeCurrent).spectateMapInfo;

        double viewChangeFactor = 0.02;
        int viewChangeRate = (int)Math.max(
                1,
                Math.min(DataBase.laukumaPlatumsSum, DataBase.laukumaAugstumsSum) / spectateMapInfo.zoomFactor * viewChangeFactor);

        if (!spectateMapInfo.playerSelected) {
            switch (numurs) { //numurs - klaviatūrā nospiestās pogas numurs
                case 87, 38 -> spectateMapInfo.centerXY[1] -= viewChangeRate; //W, up
                case 65, 37 -> spectateMapInfo.centerXY[0] -= viewChangeRate; //A, left
                case 83, 40 -> spectateMapInfo.centerXY[1] += viewChangeRate; //S, down
                case 68, 39 -> spectateMapInfo.centerXY[0] += viewChangeRate; //D, right

                default -> super.keyboardActions(numurs, thread);
            }
        } else {
            super.keyboardActions(numurs, thread);
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

        switch (reference) {
            case 1 -> CalculationsThread.pauze = !CalculationsThread.pauze;

            default -> super.footerButtonActions(reference, thread);
        }
    }

    @Override
    public void leftButtonActions(int reference, ClientThread thread){
        DrawManager.SpectateMapInfo spectateMapInfo =
                thread.dati.drawManagerList.get(thread.dati.modeCurrent).spectateMapInfo;



        switch (reference) {
            case 1 -> spectateMapInfo.selectPlayer(false, ""); //deselect player
            case 2 -> spectateMapInfo.selectPlayer(true, spectateMapInfo.pickRandomPlayer()); //select random player
            case 3 -> spectateMapInfo.chunkGrid = !spectateMapInfo.chunkGrid;
            case 4 -> spectateMapInfo.cellGrid = !spectateMapInfo.cellGrid;

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

    private static final double zoomChangeRate = 0.1, minZoom = 1;

    @Override
    public void centerButtonActions(int reference, ClientThread thread){

        switch (reference) {
            case 1 -> zoomIn(thread);
            case 2 -> zoomOut(thread);
            case 3 -> resetZoom(thread);

            default -> super.centerButtonActions(reference, thread);
        }
    }

    private void zoomIn(ClientThread thread){
        thread.dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.zoomFactor +=
                zoomChangeRate * thread.dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.zoomFactor;
    }

    private void zoomOut(ClientThread thread){
        thread.dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.zoomFactor -=
                zoomChangeRate * thread.dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.zoomFactor;

        if (thread.dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.zoomFactor < minZoom)
            resetZoom(thread);
    }

    private void resetZoom(ClientThread thread){
        thread.dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.zoomFactor =
                minZoom; 
    }
}

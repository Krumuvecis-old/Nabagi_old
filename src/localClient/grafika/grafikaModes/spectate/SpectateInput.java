package localClient.grafika.grafikaModes.spectate;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.InputActions;
import server.calculations.CalculationsThread;

public class SpectateInput extends InputActions {

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
        switch (reference) {
            case 1 -> System.out.println("Spectate mode free view - placeholder");
            case 2 -> System.out.println("Spectate mode random player selector - placeholder");
            case 3 -> System.out.println("Spectate mode MapChunk grid toggle - placeholder");
            case 4 -> System.out.println("Spectate mode MapCell grid toggle - placeholder");

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

package localClient.grafika.grafikaModes.develop;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import localClient.grafika.grafikaParts.InputActions;
import server.calculations.CalculationsThread;

public class DevelopInput extends InputActions {

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.spectate;

            default -> super.headerButtonActions(reference, thread);
        }
    }

    @Override
    public void footerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.grafikasDati.drawLayoutGrid = !thread.dati.grafikasDati.drawLayoutGrid;
            case 2 -> CalculationsThread.pauze = !CalculationsThread.pauze;

            default -> super.footerButtonActions(reference, thread);
        }
    }

    @Override
    public void leftButtonActions(int reference, ClientThread thread){

        DrawManager.DevelopTabloInfo developTabloInfo =
                thread.dati.drawManagerList.get(Dati.ModeOption.develop).developTabloInfo;

        switch (reference) {
            case 1 -> {
                if (developTabloInfo.tabloCurrent == DrawManager.DevelopTabloInfo.TabloMode.tablo1)
                    developTabloInfo.tabloCurrent = DrawManager.DevelopTabloInfo.TabloMode.none;
                else developTabloInfo.tabloCurrent = DrawManager.DevelopTabloInfo.TabloMode.tablo1;
            }
            case 2 -> {
                if (developTabloInfo.tabloCurrent == DrawManager.DevelopTabloInfo.TabloMode.tablo2)
                    developTabloInfo.tabloCurrent = DrawManager.DevelopTabloInfo.TabloMode.none;
                else developTabloInfo.tabloCurrent = DrawManager.DevelopTabloInfo.TabloMode.tablo2;
            }
            case 3 -> {
                if (developTabloInfo.tabloCurrent == DrawManager.DevelopTabloInfo.TabloMode.tablo3)
                    developTabloInfo.tabloCurrent = DrawManager.DevelopTabloInfo.TabloMode.none;
                else developTabloInfo.tabloCurrent = DrawManager.DevelopTabloInfo.TabloMode.tablo3;
            }

            default -> super.leftButtonActions(reference, thread);
        }
    }

    @Override
    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.grafikasDati.drawClientDiagnosticsInfo = !thread.dati.grafikasDati.drawClientDiagnosticsInfo;
            case 2 -> thread.dati.grafikasDati.drawCalculationTime = !thread.dati.grafikasDati.drawCalculationTime;
            case 3 -> thread.dati.grafikasDati.drawSampleImages = !thread.dati.grafikasDati.drawSampleImages;
            case 4 -> thread.dati.grafikasDati.drawColorWheel = !thread.dati.grafikasDati.drawColorWheel;

            default -> super.rightButtonActions(reference, thread);
        }
    }

    @Override
    public void centerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("develop mode scroll up placeholder");
            case 2 -> System.out.println("develop mode scroll down placeholder");
            case 3 -> thread.dati.drawManagerList.get(Dati.ModeOption.develop).layout.playerSelected = true;
            case 4 -> thread.dati.drawManagerList.get(Dati.ModeOption.develop).layout.playerSelected = false;

            default -> super.centerButtonActions(reference, thread);
        }
    }

}

package localClient.grafika.grafikaModes.develop;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.InputActions;
import server.calculations.CalculationsThread;

public class DevelopInput extends InputActions {

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.settings;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.setup;

            default -> System.out.println("ServerUIThread DevelopHeader reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void footerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.grafikasDati.drawLayoutGrid = !thread.dati.grafikasDati.drawLayoutGrid;
            case 2 -> CalculationsThread.pauze = !CalculationsThread.pauze;

            default -> System.out.println("ServerUIThread DevelopFooter reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void leftButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.grafikasDati.drawInputDiagnosticsPanel = !thread.dati.grafikasDati.drawInputDiagnosticsPanel;
            case 2 -> thread.dati.grafikasDati.drawCalculationTime = !thread.dati.grafikasDati.drawCalculationTime;
            case 3 -> thread.dati.grafikasDati.drawSampleImages = !thread.dati.grafikasDati.drawSampleImages;

            default -> System.out.println("ServerUIThread DevelopLeftPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("develop mode right panel button 1");
            case 2 -> System.out.println("develop mode right panel button 2");
            case 3 -> System.out.println("develop mode right panel button 3");

            default -> System.out.println("ServerUIThread DevelopRightPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void centerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("develop mode scroll up placeholder");
            case 2 -> System.out.println("develop mode scroll down placeholder");
            case 3 -> System.out.println("develop mode selector placeholder");

            default -> System.out.println("ServerUIThread DevelopCenterPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

}

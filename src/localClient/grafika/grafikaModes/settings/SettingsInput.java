package localClient.grafika.grafikaModes.settings;

import localClient.ClientThread;
import localClient.Dati;
import localClient.FileHandler;
import localClient.grafika.grafikaParts.InputActions;

public class SettingsInput extends InputActions {

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;

            default -> super.headerButtonActions(reference, thread);
        }
    }

    @Override
    public void centerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.grafikasDati.colorPalette.pickPreset(0);
            case 2 -> thread.dati.grafikasDati.colorPalette.pickPreset(1);
            case 3 -> thread.dati.grafikasDati.colorPalette.pickPreset(2);
            case 4 -> FileHandler.loadSettings(thread.dati, thread.grafika.ekrans);
            case 5 -> FileHandler.saveSettings(thread.dati);

            default -> super.centerButtonActions(reference, thread);
        }
    }

}

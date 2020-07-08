package server;

import server.calculations.CalculationsThread;
import server.userInterface.ServerUIThread;
import localClient.ClientThread;

public class ServerManager {
    private static String versija;

    private static final String className = "ServerManager",
            consoleOut = className + ": ";

    public static void launch(String _versija){
        //šī metode tiek izsaukta pašā projekta sākumā - no UI palaidīs serveri
        versija = _versija;
        startServer(versija);
        new ServerUIThread();
    }

    private static void startServer(String versija) {
        if(CalculationsThread.running){
            System.out.println(consoleOut + "Server already running!");
        } else {
            System.out.println(consoleOut + "Starting server.");

            server.calculations.Initializator.main(versija); //inicializācija un sākuma ģeneratori
            new CalculationsThread(); //pastāvīgs apskats
        }
    }

    public static void newLocalClient(){
        System.out.println(consoleOut + "Starting new localClient.");
        new ClientThread(); //palaiž grafisko daļu
    }

}

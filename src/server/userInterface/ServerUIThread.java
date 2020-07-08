package server.userInterface;

import server.ServerManager;
import server.calculations.CalculationsThread;

public class ServerUIThread implements Runnable {
    private static final String threadName = "ServerUIThread",
            consoleOut = threadName + ": ";

    private static boolean running = false,
            localClientStartedOnce = false;

    public ServerUIThread(){
        running = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(consoleOut + "Running.");
        while (running){

            if(!CalculationsThread.pauze) CalculationsThread.pauze = true; //servera nopauzçðanas opcija
            if(!localClientStartedOnce) startLocalClient();

            try{
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(consoleOut + "Finished.");
    }

    private static void startLocalClient(){
        ServerManager.newLocalClient();
        localClientStartedOnce = true;
    }

}

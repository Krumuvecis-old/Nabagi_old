package server.userInterface;

import server.ServerManager;
import server.calculations.CalculationsThread;

public class ServerUIThread implements Runnable {
    private static final String threadName = "ServerUIThread",
            consoleOut = threadName + ": ";

    private static boolean running = false;

    static int skaitlis1 = 0, skaitlis2 = 0; //temporary - pārbaudei

    public ServerUIThread(){
        running = true;
        UserInterface.start(threadName); //inicializē grafiku
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(consoleOut + "Running.");
        while (running){

            UserInterface.refresh(); //grafikas update

            skaitlis1 += 13; //temporary - pārbaudei
            //System.out.println(consoleOut + "Skaitlis: " + skaitlis);

            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(consoleOut + "Finished.");
    }

}

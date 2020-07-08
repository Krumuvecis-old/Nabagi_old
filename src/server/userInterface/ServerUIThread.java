package server.userInterface;

import server.ServerManager;
import server.calculations.CalculationsThread;

public class ServerUIThread implements Runnable {
    private static final String threadName = "ServerUIThread",
            consoleOut = threadName + ": ";

    private static boolean running = false;

    static int skaitlis = 0; //temporary - pârbaudei

    public ServerUIThread(){
        running = true;
        UserInterface.start(threadName); //inicializç grafiku
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(consoleOut + "Running.");
        while (running){

            UserInterface.refresh(); //grafikas update

            skaitlis++; //temporary - pârbaudei
            System.out.println(consoleOut + "Skaitlis: " + skaitlis);

            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(consoleOut + "Finished.");
    }

}

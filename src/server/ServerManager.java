package server;

public class ServerManager {

    public static void start(String versija) {
        System.out.println("Server starting");

        server.calculations.Initializator.main(versija);
        server.calculations.Main.main();

        //new PanelGUI(); //varētu uztaisīt thread?

    }

    public static void newLocalClient(){
        //new ClientThread(); //palaiž jaunu ClientThread
        System.out.println("jauna lokāla klienta palaišanas placeholder");
    }

}

import server.ServerManager;

public class Setup {

    //pati pirmâ klase, kura tâlâk palaiþ pârçjâs
    private static String versija = "0.11.3 (beta)";

    public static void main(String... args) {
        System.out.println("Nabagi (versija: " + versija + ")");

        ServerManager.start(versija);
    }

}

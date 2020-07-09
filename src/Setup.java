import server.ServerManager;

public class Setup {

    //pati pirmâ klase, kura tâlâk palaiþ pârçjâs
    private static final String nosaukums = "Nabagi",
            versija = "0.11.4 (beta)";

    public static void main(String... args) {
        System.out.println(nosaukums + " (versija: " + versija + ")");

        ServerManager.launch(versija);
    }

}

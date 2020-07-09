import server.ServerManager;

public class Setup {

    //pati pirm� klase, kura t�l�k palai� p�r�j�s
    private static final String nosaukums = "Nabagi",
            versija = "0.11.4 (beta)";

    public static void main(String... args) {
        System.out.println(nosaukums + " (versija: " + versija + ")");

        ServerManager.launch(versija);
    }

}

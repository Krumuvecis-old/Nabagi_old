import server.ServerManager;

public class Setup {

    //pati pirm� klase, kura t�l�k palai� p�r�j�s
    private static String versija = "0.11.3 (beta)";

    public static void main(String... args) {
        System.out.println("Nabagi (versija: " + versija + ")");

        ServerManager.start(versija);
    }

}

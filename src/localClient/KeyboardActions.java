package localClient;

class KeyboardActions {

    protected static void main(int[] pogas){
        //nospiesto keyboard pogu nolasîðanai notikumiem

        for (int i=0; i<pogas.length; i++) { //iziet cauri visâm pogâm
            keyboardNotikums(pogas[i]);
        }
    }

    //private static int[][] debounceList = new int[][]{};
    //private static int debounceTimer=5;

    private static void keyboardNotikums(int  numurs){
        //numurs - klaviatûrâ nospiestâs pogas numurs

        switch (numurs) {
            case 32 -> System.out.println("piespiests Space");
            case 87 -> System.out.println("piespiests W");
            case 65 -> System.out.println("piespiests A");
            case 83 -> System.out.println("piespiests S");
            case 68 -> System.out.println("piespiests D");
            default -> System.out.println("piespiesta nedefinçta keyboard poga");
        }
    }
}

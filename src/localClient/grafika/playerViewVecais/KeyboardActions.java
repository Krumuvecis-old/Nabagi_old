package localClient.grafika.playerViewVecais;

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

        if (numurs==32){ //Space
            System.out.println("player piespiests Space");

        } else if(numurs==87){ //W
            System.out.println("player piespiests W");

        } else if(numurs==65){ //A
            System.out.println("player piespiests A");

        } else if(numurs==83){ //S
            System.out.println("player piespiests S");

        } else if(numurs==68){ //D
            System.out.println("player piespiests D");

        }

    }
}

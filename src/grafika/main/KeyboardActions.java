package grafika.main;

class KeyboardActions {


    protected static void main(int[] pogas){
        //nospiesto keyboard pogu nolas��anai notikumiem

        for (int i=0; i<pogas.length; i++) { //iziet cauri vis�m pog�m
            keyboardNotikums(pogas[i]);
        }
    }

    //private static int[][] debounceList = new int[][]{};
    //private static int debounceTimer=5;

    private static void keyboardNotikums(int  numurs){

        //te piem�ram ja numurs==xx notiek kaut kas - piem�ram main.cilvekuList.cilveks.darbibas.kautkas
        //	vai focusfind=player+1, zoom++ / zoom--

        if (numurs==32){ //Space
            System.out.println("piespiests Space");

        } else if(numurs==87){ //W
            System.out.println("piespiests W");

        } else if(numurs==65){ //A
            System.out.println("piespiests A");

        } else if(numurs==83){ //S
            System.out.println("piespiests S");

        } else if(numurs==68){ //D
            System.out.println("piespiests D");

        }

    }
}

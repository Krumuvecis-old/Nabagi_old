package grafika.main;

class LayoutDati {

    //ðî klase ir pârpalikums no vecâ varianta

    private static int wx=1000, wy=700, x0=0,
            wy1=30, y01=0, //header

            wy2, y02, //body

                y2wx1=150, y2x01=0, //pirmais panelis kreisajâ pusç
                    y2x1wy1=400, y2x1y01,
                    y2x1wy2, y2x1y02,
                    y2x1wy3=150, y2x1y03,
                y2wx2=200, y2x02 = y2x01 + y2wx1, //otrais panelis kreisajâ pusç
                y2wx3, y2x03 = y2x02 + y2wx2, //centra panelis
                y2wx4=50, y2x04, //panelis labajâ pusç

            wy3=50, y03; //footer

    private static void initializeHeader(){



    }

    private static void initializeBody(){

        //galvenâ inicializâcija
        y02 = y01 + wy1;
        wy2 = Math.max( 0, wy - (wy1 + wy3) );

        //vertikâlo paneïu inicializâcija
        y2wx3 = Math.max( 0, wx - (y2wx1 + y2wx2 + y2wx4) );
        y2x04 = y2x03 + y2wx3;

        //pats kreisais panelis
        y2x1y01 = y02;
        y2x1y02 = y2x1y01 + y2x1wy1;
        y2x1wy2 = Math.max( 0, wy2 - (y2x1wy1 + y2x1wy3) );
        y2x1y03 = y2x1y02 + y2x1wy2;


    }

    private static void initializeFooter(){

        wy3 = Math.max( 0, wy - (wy1 + wy3) );
        y03 = y02 + wy3;

    }

}

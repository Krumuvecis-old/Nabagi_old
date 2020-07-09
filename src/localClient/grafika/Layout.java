package localClient.grafika;

public class Layout {
    static int[] ekransLocation = {10,10};
    private static final int[] ekransCorrection = {0, 0};

    public int ekranaPlatums = 1000 + ekransCorrection[0], ekranaAugstums = 700 + ekransCorrection[1],
            headerY = 0, //starting point of header (in pixels to offset title bar)
            headerAugstums = 40, //header height (in pixels)

            panelY, //calculated starting point for panels (in pixels from top)
            panelAugstums, //calculated height for panels (in pixels)
            panel1X=0, //panel1 x starting point (in pixels from left)
            panel1platums = 150, //panel1 x width (in pixels)
            panel2X, //calculated panel2 x starting point (in pixels from left)
            panel2platums = 150, //panel2 x width (in pixels)
            panel3X, //calculated panel3 x starting point (in pixels from left)
            panel3platums, //calculated panel3 x width (in pixels)
            panel3RightBorder = 0, //panel3 offset from right side (in pixels)
            panel3Border = 0, //panel3 border width (in pixels)
            panel3ContentsX, panel3ContentsY, panel3contentsWX, panel3contentsWY, //starting point and width&height for contents of the panel3 (in pixels)

            footerAugstums = 40, //footer height (in pixels)
            footerY; //starting point (in pixels from bottom)


    public Layout(){
        updateCalculatedValues();
    }

    private void updateCalculatedValues(){
        panelY = headerY + headerAugstums;
        footerY = Math.max(panelY, ekranaAugstums - footerAugstums);

        panelAugstums=Math.max(0, footerY - panelY);
        panel2X = panel1X + panel1platums;
        panel3X = panel2X + panel2platums;
        panel3platums = Math.max(0, ekranaPlatums - panel3X - panel3RightBorder);

        panel3ContentsX = panel3X + panel3Border;
        panel3ContentsY = panelY + panel3Border;
        panel3contentsWX = panel3platums - panel3Border * 2;
        panel3contentsWY = panelAugstums - panel3Border * 2;
    }

    public void update(Grafika grafika){
        ekranaPlatums = grafika.getWidth();
        ekranaAugstums = grafika.getHeight();
        updateCalculatedValues();
    }


//    //zemâk vecâs vçrtîbas no vecâs klases
//
//    private static int wx=1000, wy=700, x0=0,
//            wy1=30, y01=0, //header
//
//    wy2, y02, //body
//
//    y2wx1=150, y2x01=0, //pirmais panelis kreisajâ pusç
//            y2x1wy1=400, y2x1y01,
//            y2x1wy2, y2x1y02,
//            y2x1wy3=150, y2x1y03,
//            y2wx2=200, y2x02 = y2x01 + y2wx1, //otrais panelis kreisajâ pusç
//            y2wx3, y2x03 = y2x02 + y2wx2, //centra panelis
//            y2wx4=50, y2x04, //panelis labajâ pusç
//
//    wy3=50, y03; //footer
//
//    private static void initializeHeader(){
//
//
//
//    }
//
//    private static void initializeBody(){
//
//        //galvenâ inicializâcija
//        y02 = y01 + wy1;
//        wy2 = Math.max( 0, wy - (wy1 + wy3) );
//
//        //vertikâlo paneïu inicializâcija
//        y2wx3 = Math.max( 0, wx - (y2wx1 + y2wx2 + y2wx4) );
//        y2x04 = y2x03 + y2wx3;
//
//        //pats kreisais panelis
//        y2x1y01 = y02;
//        y2x1y02 = y2x1y01 + y2x1wy1;
//        y2x1wy2 = Math.max( 0, wy2 - (y2x1wy1 + y2x1wy3) );
//        y2x1y03 = y2x1y02 + y2x1wy2;
//
//
//    }
//
//    private static void initializeFooter(){
//
//        wy3 = Math.max( 0, wy - (wy1 + wy3) );
//        y03 = y02 + wy3;
//
//    }

}

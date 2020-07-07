package grafika.main;

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


    Layout(){
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

    void update(Grafika grafika){
        ekranaPlatums = grafika.getWidth();
        ekranaAugstums = grafika.getHeight();
        updateCalculatedValues();
    }

}

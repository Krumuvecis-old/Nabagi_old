package localClient.grafika.grafikaParts;

public class SampleLayout {
    public int ekranaPlatums, ekranaAugstums,

            headerY = 0, //starting point of header (in pixels to offset title bar)
            headerAugstums = 40, //header height (in pixels)

            panelY, //calculated starting point for panels (in pixels from top)
            panelAugstums, //calculated height for panels (in pixels)

            panelLX = 0, //panel1 x starting point (in pixels from left, acts as offset)
            panelLPlatums = 250, //panel1 x width (in pixels)
            panelRX, panelROffset = 0, //calculated panel2 x starting point (in pixels from left)
            panelRPlatums = panelLPlatums, //panel2 x width (in pixels)
            centerPanelX, //calculated panel3 x starting point (in pixels from left)
            centerPanelPlatums, //calculated panel3 x width (in pixels)
            centerPanelBorder = 0, //centerPanel border width (in pixels)
            centerPanelContentsX, centerPanelContentsY, //starting point for centerPanel contents (in pixels)
            centerPanelContentsWX, centerPanelContentsWY, //width&height for centerPanel contents (in pixels)

            footerAugstums = 40, //footer height (in pixels)
            footerY, footerOffset = 0; //starting point and offset (in pixels from top)

    public boolean playerSelected = false;

    public SampleLayout(int _ekranaPlatums, int _ekranaAugstums){
        updateCalculatedValues(_ekranaPlatums, _ekranaAugstums);
    }

    public void updateCalculatedValues(int _ekranaPlatums, int _ekranaAugstums){
        ekranaPlatums = _ekranaPlatums;
        ekranaAugstums = _ekranaAugstums;

        panelY = headerY + headerAugstums;
        footerY = Math.max(panelY, ekranaAugstums - footerAugstums + footerOffset);

        panelAugstums=Math.max(0, footerY - panelY);
        centerPanelX = panelLX + panelLPlatums;
        panelRX = Math.max(centerPanelX, ekranaPlatums - panelRPlatums + panelROffset);
        centerPanelPlatums = Math.max(0, panelRX - centerPanelX);

        centerPanelContentsX = Math.min(panelRX, centerPanelX + centerPanelBorder);
        centerPanelContentsWX = Math.max(0, panelRX - centerPanelX - centerPanelBorder * 2);

        centerPanelContentsY = Math.min(footerY, panelY + centerPanelBorder);
        centerPanelContentsWY = Math.max(0, footerY - panelY - centerPanelBorder * 2);
    }

}

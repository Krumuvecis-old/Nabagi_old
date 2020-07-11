package localClient.grafika;

import localClient.ColorPalette;
import localClient.FileHandler;
import server.dataBase.DataBase;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GrafikasDati {

    public static String ekranaNosaukums = "Nabagi - versija: "+ DataBase.versija;

    public String windowTitle = GrafikasDati.ekranaNosaukums + ", ClientWindow";
    public Color backgroundColor = Color.black;

    static int[] ekransLocation = {10,10};
    private static final int[] ekransCorrection = {0, 0};
    public int ekranaPlatums = 1340 + ekransCorrection[0], ekranaAugstums = 710 + ekransCorrection[1];

    public boolean drawLayoutGrid = false;
    public Color layoutGridColor = Color.red;

    public ColorPalette colorPalette;

    public boolean drawCalculationTime = true,
            drawClientDiagnosticsInfo = true,
            drawSampleImages = false,
            drawColorWheel = false;

    public HashMap<String, Image> images = new HashMap<>();
    static final String imageLocation = "src/localClient/samplePictures/";

    public GrafikasDati(){
        ColorPalette.generatePresetPalettes();
        ColorPalette defaultPalette = ColorPalette.presetPalettes.get(0);
        colorPalette = new ColorPalette(defaultPalette.pair1, defaultPalette.pair2, defaultPalette.pair3);

        String[][] imageNames = new String[][]{
                {"Zvaigzne.png", "zvaigzne"},
                {"Banans.png", "banana"},
                {"panelBackground.png", "panelBackground"},
                {"welcomeSign.png", "welcomeSign"}
        };
        FileHandler.loadSprites(images, imageNames, imageLocation);
    }

    public static Image rotateImage(Image _image, double fi){

        /*
         * ðî metode pagrieþ bildi par fi grâdiem
         * (metode kopçta no interneta)
         */

        BufferedImage image = (BufferedImage) _image;
        double rads = Math.toRadians(fi);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        BufferedImage rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);
        return rotatedImage;
    }

}

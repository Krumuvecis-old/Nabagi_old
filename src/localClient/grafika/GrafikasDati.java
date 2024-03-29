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

    public static String programTitle = "Nabagi - versija: " + DataBase.versija;
    public static final String windowName = "ClientWindow";
    public String windowTitle = programTitle + ", " + windowName;
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
    static final String imageLocation = "media/";

    public GrafikasDati(){
        ColorPalette.generatePresetPalettes();
        ColorPalette defaultPalette = ColorPalette.presetPalettes.get(0);
        colorPalette = new ColorPalette(defaultPalette.pair1, defaultPalette.pair2, defaultPalette.pair3);

        String[][] imageNames = new String[][]{
                {"nabagiLogo.png", "nabagiLogo"},
                {"welcomeSign.png", "welcomeSign"},
                {"panelBackground.png", "panelBackground"},

                {"sprites/cilveks.png", "cilveks"},
                {"sprites/Zvaigzne.png", "zvaigzne"},
                {"sprites/items/zelts.png", "zelts"},
                {"sprites/items/Banans.png", "banana"},

                {"sprites/terrain/dirt.png", "terrainDirt"},
                {"sprites/terrain/grass.png", "terrainGrass"},
                {"sprites/terrain/sand.png", "terrainSand"},
                {"sprites/terrain/stone.png", "terrainStone"}
        };
        FileHandler.loadSprites(images, imageNames, imageLocation);
    }

    public static void drawRotatedImage(Graphics g, GrafikasDati grafikasDati, String imageName, int[] centerLoc, int[] size, double fi){

        double cosComponent = Math.abs(Math.cos(Math.toRadians(fi))),
                sinComponent = Math.abs(Math.sin(Math.toRadians(fi)));

        int[] rotatedSize = new int[]{
                (int)(size[0] * cosComponent + size[1] * sinComponent),
                (int)(size[0] * sinComponent + size[1] * cosComponent)};

        int[] rotatedXY = new int[]{
                centerLoc[0] - rotatedSize[0] / 2,
                centerLoc[1] - rotatedSize[1] / 2};

        g.drawImage(rotateImage(grafikasDati.images.get(imageName), fi),
                rotatedXY[0], rotatedXY[1],
                rotatedSize[0], rotatedSize[1],null);

    }

    public static Image rotateImage(Image _image, double fi){

        /*
         * �� metode pagrie� bildi par fi gr�diem
         * (metode kop�ta no interneta)
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

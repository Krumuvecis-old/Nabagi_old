package localClient;

import java.awt.*;
import java.util.ArrayList;

public class ColorPalette {

    public Color[] pair1, //headerim & footerim
            pair2, //sânu paneïiem
            pair3; //centra panelim

    public ColorPalette(Color[] colorPair1, Color[] colorPair2, Color[] colorPair3){
       updatePalette(colorPair1, colorPair2, colorPair3);
    }

    private void updatePalette(Color[] colorPair1, Color[] colorPair2, Color[] colorPair3){
        pair1 = new Color[]{colorPair1[0], colorPair1[1]};
        pair2 = new Color[]{colorPair2[0], colorPair2[1]};
        pair3 = new Color[]{colorPair3[0], colorPair3[1]};
    }

    public static ArrayList<ColorPalette> presetPalettes;

    public static void generatePresetPalettes(){
        presetPalettes = new ArrayList<>();
        presetPalettes.add(new ColorPalette(
                new Color[]{
                        new Color(65,35,30),
                        new Color(160, 120, 50)},
                new Color[]{
                        new Color(100,110,100),
                        new Color(20,0,0)},
                new Color[]{
                        new Color(50,100,5),
                        new Color(30,10,0)}));

        presetPalettes.add(new ColorPalette(
                new Color[]{
                        new Color(30,35,65),
                        new Color(50, 120, 160)},
                new Color[]{
                        new Color(120,120,160),
                        new Color(0,0,20)},
                new Color[]{
                        new Color(80,80,100),
                        new Color(5,5,30)}));

        presetPalettes.add(new ColorPalette(
                new Color[]{
                        new Color(150,25,20),
                        new Color(200, 80, 80)},
                new Color[]{
                        new Color(160,120,120),
                        new Color(60,0,0)},
                new Color[]{
                        new Color(170,50,40),
                        new Color(40,0,0)}));
    }

    public void pickPreset(int number){
        ColorPalette presetPalette = presetPalettes.get(0); //default
        for (int i=0; i<presetPalettes.size(); i++){
            if(i == number) {
                presetPalette = presetPalettes.get(i);
                break;
            }
        }
        updatePalette(presetPalette.pair1, presetPalette.pair2, presetPalette.pair3);
    }

}

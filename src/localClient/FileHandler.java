package localClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class FileHandler {

    private static final Charset encoding = Charset.forName("Cp1257");
    private static final String readSeparator = "READSEPARATOR",
                                valueSeparator = " : ";

    private static boolean checkFileStatus(String filePath){
        System.out.println("Checking file status at " + filePath);
        File fileToCheck = new File(filePath);

        if (!fileToCheck.exists()) System.out.println("File not found");
        else if (!fileToCheck.canRead()) System.out.println("File not readable");
        else return true;
        return false;
    }

    private static List<String> readFile(String filePath){
        System.out.println("Reading file at " + filePath);
        try {
            List<String> linesRead = readAllLines(Path.of(filePath), encoding);
            if (linesRead.size() < 1) System.out.println("File empty or incorrect encoding");
            else {
                System.out.println("Number of lines read: " + linesRead.size() + " in encoding: " + encoding);
                return linesRead;
            }
        } catch (IOException e) {
            System.out.println("An unexpected error occurred while reading file");
            e.printStackTrace();
        }
        return null;
    }

    public static void loadSettings(Dati dati, JFrame ekrans){
        String filePath = Dati.settingsFileLocation + Dati.settingsFileName;
        if (checkFileStatus(filePath)) {
            List<String> readLines = readFile(filePath);
            if (readLines != null) {
                String[] settingsLines = separateContents(readLines)[1];
                setLoadedSettings(settingsLines, dati, ekrans);
            }
        }
        System.out.println("File loading complete.");
    }

    private static String[][] separateContents(List<String> linesRead){
        int contentPartNumber = 0; // 0 - header, 1 - body, 2 - footer
        List<String> contentPart1 = new ArrayList<>();
        List<String> contentPart2 = new ArrayList<>();
        List<String> contentPart3 = new ArrayList<>();

        for (int i=0; i<linesRead.size(); i++) {
            if(linesRead.get(i).equals(readSeparator)) {
                contentPartNumber++;
                continue;
            }

            switch (contentPartNumber){
                case 0 -> contentPart1.add(linesRead.get(i));
                case 1 -> contentPart2.add(linesRead.get(i));
                case 2 -> contentPart3.add(linesRead.get(i));
            }
        }

        return new String[][]{
                contentPart1.toArray(new String[]{}),
                contentPart2.toArray(new String[]{}),
                contentPart3.toArray(new String[]{})
        };
    }

    private static void setLoadedSettings(String[] settingsList, Dati dati, JFrame ekrans){
        System.out.println("Number of relevant lines: " + settingsList.length);
        for (int i=0; i<settingsList.length; i++){
            String[] splitString = settingsList[i].split(valueSeparator);
            String settingName = splitString[0];
            String settingValue = splitString[1];
            System.out.println("Updating " + settingName + " to " + settingValue);

            switch (settingName) {
                case "ekranaPlatums" -> ekrans.setSize(Integer.parseInt(settingValue), ekrans.getHeight());
                case "ekranaAugstums" -> ekrans.setSize(ekrans.getWidth(), Integer.parseInt(settingValue));
                case "palette" -> dati.colorPalette.pickPreset(Integer.parseInt(settingValue));

                default -> System.out.println("Unidentified value: " + settingName);
            }
        }
    }

    public static void saveSettings(Dati dati){
        String filePath = Dati.settingsFileLocation + Dati.settingsFileName;

        String[] headerLines = new String[]{},
                footerLines = new String[]{};

        //nolasa esoðos header&footer, ja tâdi ir
        if (checkFileStatus(filePath)){
            List<String> readLines = readFile(filePath);
            if (readLines != null) {
                headerLines = separateContents(readLines)[0];
                footerLines = separateContents(readLines)[2];
            }
        }

        String[][] settingsLines = new String[][]{
                {"ekranaPlatums", String.valueOf(dati.layout.ekranaPlatums + 16)},
                {"ekranaAugstums", String.valueOf(dati.layout.ekranaAugstums + 39)},
                {"palette", "0"}
        };

        writeToFile(filePath, headerLines, settingsLines, footerLines);
        System.out.println("File writing complete.");
    }

    private static void writeToFile(String filePath, String[] headerLines, String[][] settingsArray, String[] footerLines){
        try {
            File settingsFile = new File(filePath);
            if (settingsFile.delete()) System.out.println("Previous file deleted");
            if (settingsFile.createNewFile()) System.out.println("New file created");

            List<String> writableContents = new ArrayList<>(Arrays.asList(headerLines));
            writableContents.add(readSeparator);

            for(int i=0; i<settingsArray.length; i++)
                writableContents.add(settingsArray[i][0] + valueSeparator + settingsArray[i][1]);

            writableContents.add(readSeparator);
            writableContents.addAll(Arrays.asList(footerLines));

            Files.write(Path.of(filePath), writableContents, encoding);

        } catch (IOException e) {
            System.out.println("An unexpected error occurred while creating at " + filePath);
            e.printStackTrace();
        }

    }

    public static void loadSprites(HashMap<String, Image> imageList,
                                   String[][] imageNames,
                                   String imageLocation){
        for(int i=0; i<imageNames.length; i++){
            String imagePath = imageLocation + imageNames[i][0];
            if (checkFileStatus(imagePath)){
                imageList.put(imageNames[i][1], readSingleImage(imagePath));
                System.out.println("Finished loading: " + imageNames[i][0]);
            }
        }
        System.out.println("Image loading complete.");
    }

    private static Image readSingleImage(String filePath){
        File imageFile = new File(filePath);
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Unexpected error occured at " + filePath);
            e.printStackTrace();
        }
        return null;
    }

}

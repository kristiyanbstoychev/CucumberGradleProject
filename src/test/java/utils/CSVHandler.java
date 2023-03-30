package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {

    public static final String delimiter = ",";

    //reads any given csv file and saves it into a specified list of strings
    public static void readCSVFileAndSaveContentAsListOfStrings(String csvFile, List<String> csvData) {
        try {
            File file = new File(csvFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String[] csvArray;
            String line = " ";
            while ((line = bufferedReader.readLine()) != null) {
                csvArray = line.split(delimiter);
                csvData.addAll(Arrays.asList(csvArray));
            }
            bufferedReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}

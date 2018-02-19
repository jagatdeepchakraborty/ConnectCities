package com.mc.connectcities.util;

import com.mc.connectcities.exception.ProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CommonUtils {

    public static ArrayList<String> readFileToArraylist(String fileLocation) throws ProcessingException {
        ArrayList<String> fileDataList = new ArrayList<>();
        String filePath = new File("").getAbsolutePath();
        try {
            File file = new File(fileLocation);
            //Assuming /src/ is from tests
            FileReader fileReader = fileLocation.startsWith("/src/") ? new FileReader(filePath + fileLocation) : new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileDataList.add(line);
            }
            fileReader.close();
            return fileDataList;
        } catch (Exception e) {
            throw new ProcessingException(e, "Caught exception while trying to read file. " + e.getMessage());
        }
    }
}

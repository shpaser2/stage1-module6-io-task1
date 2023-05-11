package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);

            String str = new String(data, "UTF-8");
            String[] lines = str.split("\n");

            for (String line : lines) {
                String[] parts = line.split(": ");
                parts[1] = parts[1].trim();
                switch (parts[0]) {
                    case "Name":
                        profile.setName(parts[1]);
                        break;
                    case "Age":
                        profile.setAge(Integer.parseInt(parts[1]));
                        break;
                    case "Email":
                        profile.setEmail(parts[1]);
                        break;
                    case "Phone":
                        profile.setPhone(Long.parseLong(parts[1]));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }
}

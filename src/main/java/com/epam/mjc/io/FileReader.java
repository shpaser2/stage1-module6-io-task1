package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            int result = fis.read(data);
            if (result == -1) {
                throw new IOException("unsuccessful reading of file");
            }

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
                    default:
                        throw new IOException("wrong key in the given profile");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }
}

package edu.avada.course.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Controllers {
    private final static String SERVER_PREFIX = "src/main/resources/static";
    public final static String CONTACTS_FILE_PATH = "src/main/resources/support_files/contacts";
    public final static String BINDING_FILE_PATH = "src/main/resources/support_files/binding";
    public final static String ABOUT_FILE_PATH = "src/main/resources/support_files/about";

    public static String saveFile(
            String picturesPath,
            String fileName,
            String timestamp,
            String ext,
            byte[] bytes
    ) {
        String dirPath = SERVER_PREFIX + "/" + picturesPath;
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        for (File file: files) {
            if (file.isFile() && file.getName().contains(fileName)) {
                file.delete();
            }
        }
        String fullFileName = dirPath + "/" + fileName + "_" + timestamp + "." + ext ;
        try {
            Files.write(Path.of(fullFileName), bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "/" + picturesPath + "/" + fileName + "_" + timestamp + "." + ext;
    }
}

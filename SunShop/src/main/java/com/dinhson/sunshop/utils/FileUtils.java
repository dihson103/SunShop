package com.dinhson.sunshop.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    private static void saveToMain(String fileName, MultipartFile multipartFile){
        Path uploadDirectory = Paths.get("src/main/resources/static/img");
        save(fileName, multipartFile, uploadDirectory);
    }

    private static void saveToTarget(String fileName, MultipartFile multipartFile){
        Path uploadDirectory = Paths.get("target/classes/static/img");
        save(fileName, multipartFile, uploadDirectory);
    }

    private static void save(String fileName, MultipartFile multipartFile, Path uploadDirectory){
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFile(String fileName, MultipartFile multipartFile){
        saveToMain(fileName, multipartFile);
        saveToTarget(fileName, multipartFile);
    }
}

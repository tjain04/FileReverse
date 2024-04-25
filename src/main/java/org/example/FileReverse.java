package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class FileReverse {

    public static void main(String[] args) {
        String filePath = "file.txt";
        String revereFilePath = "file_reverse.txt";
        // FileReverse fileReverse = new FileReverse();
        //fileReverse.reverseFile();
    }

    public void reverseFile(String filePath, String revereFilePath) {

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("File reverse operation started: " + startTime);


        String content = "";
        try {
            content = Files.lines(Paths.get(filePath))
                    .collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);
            String reverseStr = new StringBuilder(content).reverse().toString();

            if (Files.size(Paths.get(filePath)) > 10000) {
                Path path = Paths.get(revereFilePath);
                Files.writeString(path, reverseStr);
            } else {
                Path path = Path.of(revereFilePath);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
                    writer.write(reverseStr);
                }
            }
            LocalDateTime endTime = LocalDateTime.now();
            System.out.println("File reverse operation ended: " + endTime);
            System.out.println("File reverse operation time taken : " + Duration.between(startTime, endTime).getNano());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

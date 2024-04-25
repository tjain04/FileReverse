import org.example.FileReverse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReverseTest {
    private static final Logger logger = Logger.getLogger(FileReverseTest.class.getName());

    @BeforeAll
    static void setup() {
        logger.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        logger.info("@BeforeEach - executes before each test method in this class");
    }

    @Test
    void testFileReverse() throws IOException {
        //content in file
        String inputStr= "ABCD";
        String filePath = "file.txt";
        Files.writeString(Paths.get(filePath), inputStr);

        String reverseFilePath = "file_reverse.txt";

        FileReverse fileReverse = new FileReverse();
        fileReverse.reverseFile(filePath, reverseFilePath);

        Path path = Paths.get(reverseFilePath);
        assertTrue(Files.exists(path));
        assertEquals(Files.size(path), Files.size(Paths.get(filePath)));
        String reversedContent = Files.lines(path)
                .collect(Collectors.joining(System.lineSeparator()));

        String reversedStr = new StringBuilder(inputStr).reverse().toString();

        assertEquals(reversedContent, reversedStr);
    }
}

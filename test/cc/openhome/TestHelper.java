package cc.openhome;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import static java.nio.file.Files.newDirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;

public class TestHelper {
    public static String readAllText(Path file, String charsetName) throws IOException {
        return new String(Files.readAllBytes(file), charsetName);
    }
    
    public static void assertRemoved(Path testedDir, String replaced, String replacement, String charsetName) throws IOException {
        try(DirectoryStream<Path> pages = newDirectoryStream(testedDir, "*.{htm,html}")) {
            for (Path page: pages) { 
                Path removedHTML = Paths.get(page.toString().replaceFirst(replaced, replacement));
                String result = readAllText(page, charsetName);
                String expResult = readAllText(removedHTML, charsetName);
                assertEquals(expResult, result);
            }
        }
    }
}

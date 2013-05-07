package cc.openhome;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Files.readAllBytes;
import java.nio.file.Path;
import static org.junit.Assert.assertEquals;

public class TestHelper {
    
    public static void assertContentEquals(Path expectedPath, Path resultPath, String charsetName) throws IOException {
        String result = readAllText(resultPath, charsetName);
        String expResult = readAllText(expectedPath, charsetName);
        assertEquals(expResult, result);
    }
    
    public static String readAllText(Path file, String charsetName) throws IOException {
        return new String(readAllBytes(file), charsetName);
    }
    
    public static void assertPagesEquals(Path expected, Path result, String charsetName) throws IOException {
        try(DirectoryStream<Path> pages = newDirectoryStream(result, "*.{htm,html}")) {
            for (Path page: pages) { 
                assertContentEquals(expected.resolve(page.getFileName()), page, charsetName);
            }
        }
    }
}

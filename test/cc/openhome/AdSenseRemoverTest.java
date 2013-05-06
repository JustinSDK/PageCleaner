package cc.openhome;

import static cc.openhome.TestHelper.assertRemoved;
import static cc.openhome.TestHelper.readAllText;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AdSenseRemoverTest {
    private Path expectedPage;
    private String charsetName;
    
    @Before
    public void setUp() {
        expectedPage = Paths.get("fixtures/expected/Blog/index.html");
        charsetName = "UTF-8";
    }
    
    @Test
    public void testRemoveJsDir() throws Exception {
        Path srcDir = Paths.get("fixtures/test/AdSenseRemover/Blog1");
        AdSenseRemover.removeJsDir(srcDir, charsetName);
        
        assertRemoved(srcDir, "test/AdSenseRemover/Blog1", "expected/Blog", charsetName);
    }
    
    @Test
    public void testRemoveJsDirAndSaveAs() throws Exception {
        Path srcDir = Paths.get("fixtures/test/AdSenseRemover/Blog2");
        Path destDir = Paths.get("fixtures/dest/AdSenseRemover/Blog2");
        AdSenseRemover.removeJsDirAndSaveAs(srcDir, destDir, charsetName);
        
        assertRemoved(srcDir, "dest/AdSenseRemover/Blog2", "expected/Blog", charsetName);
    }
    
    @Test
    public void testRemoveFileJs() throws Exception {
        Path srcFile = Paths.get("fixtures/test/AdSenseRemover/index1.html");
        AdSenseRemover.removeFileJs(srcFile, charsetName);
        
        String result = readAllText(srcFile, charsetName);
        String expResult = readAllText(expectedPage, charsetName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRemoveFileJsAndSaveAs() throws IOException {
        Path srcFile = Paths.get("fixtures/test/AdSenseRemover/index2.html");
        Path destFile = Paths.get("fixtures/dest/AdSenseRemover/index2.html");
        AdSenseRemover.removeFileJsAndSaveAs(srcFile, destFile, charsetName);
        
        String result = readAllText(destFile, charsetName);
        String expResult = readAllText(expectedPage, charsetName);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveHtmlJs() throws IOException {
        Path srcFile = Paths.get("fixtures/test/AdSenseRemover/index2.html");
        String html = readAllText(srcFile, charsetName);
        String result = AdSenseRemover.removeHtmlJs(html);
        String expResult = readAllText(expectedPage, charsetName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testReadFileWithoutJs() throws IOException {
        Path srcFile = Paths.get("fixtures/test/AdSenseRemover/index2.html");
        String result = AdSenseRemover.readFileWithoutJs(srcFile, charsetName);
        String expResult = readAllText(expectedPage, charsetName);
        assertEquals(expResult, result);
    }
}

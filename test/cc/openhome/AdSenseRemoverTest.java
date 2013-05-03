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
    private Path testedDir;
    private Path testedFile;
    private Path removedFile;
    private Path destFile;
    private String charsetName;
    
    @Before
    public void setUp() {
        testedDir = Paths.get("fixtures/test/Blog");
        testedFile = Paths.get("fixtures/test/Blog/index.html");
        removedFile = Paths.get("fixtures/removed/Blog/index.html");
        destFile = Paths.get("fixtures/test/dest/index.html");
        charsetName = "UTF-8";
    }
    
    @Test
    public void testRemoveJsDir() throws Exception {
        AdSenseRemover.removeJsDir(testedDir, charsetName);
        
        assertRemoved(testedDir, "test", "removed", charsetName);
    }
    
    @Test
    public void testRemoveFileJs() throws Exception {
        AdSenseRemover.removeFileJs(testedFile, charsetName);
        
        String result = readAllText(testedFile, charsetName);
        String expResult = readAllText(removedFile, charsetName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRemoveFileJsAndSaveAs() throws IOException {
        AdSenseRemover.removeFileJsAndSaveAs(testedFile, destFile, charsetName);
        
        String result = readAllText(destFile, charsetName);
        String expResult = readAllText(removedFile, charsetName);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveHtmlJs() throws IOException {
        String html = readAllText(testedFile, charsetName);
        String result = AdSenseRemover.removeHtmlJs(html);
        String expResult = readAllText(removedFile, charsetName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testReadFileWithoutJs() throws IOException {
        String result = AdSenseRemover.readFileWithoutJs(testedFile, charsetName);
        String expResult = readAllText(removedFile, charsetName);
        assertEquals(expResult, result);
    }
}

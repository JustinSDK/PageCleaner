package cc.openhome;

import static cc.openhome.TestHelper.readAllText;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PageTest {
    private String charsetName;
    private Path expectedPath;
    private Path srcPath;
    
    @Before
    public void setUp() {
        charsetName = "UTF-8";
        srcPath = Paths.get("fixtures/test/Page/index1.html");
        expectedPath = Paths.get("fixtures/expected/Blog/index.html");
    }
    
    @Test
    public void testReadHtml() throws IOException {
        Page page = new Page(srcPath, charsetName);
        String result = page.readHtml();
        String expected = readAllText(srcPath, charsetName);
        assertEquals(expected, result);
    }
    
    @Test
    public void testCleanHtmlJs() throws IOException {
        String html = readAllText(srcPath, charsetName);
        String result = Page.cleanHtmlJs(html);
        String expResult = readAllText(expectedPath, charsetName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testHtmlEquals() {
        Page p1 = new Page(srcPath, charsetName);
        Page p2 = new Page(srcPath, charsetName);
        assertTrue(p1.htmlEquals(p2));
    }

    @Test
    public void testCopyTo() throws IOException {
        Path destPath = Paths.get("fixtures/test/dest/Page/index1.html");
        Page srcPage = new Page(srcPath, charsetName);
        Page destPage = new Page(destPath, charsetName);
        srcPage.copyTo(destPath);
        assertTrue(srcPage.htmlEquals(destPage));
    }
    
    @Test
    public void testCleanJs() throws IOException {
        Page resultPage = new Page(srcPath, charsetName);
        resultPage.cleanJs();
        Page expectedPage = new Page(expectedPath, charsetName);
        assertTrue(expectedPage.htmlEquals(resultPage));
    }
}

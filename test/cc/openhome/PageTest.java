package cc.openhome;

import static cc.openhome.TestHelper.assertContentEquals;
import static cc.openhome.TestHelper.readAllText;
import java.io.IOException;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PageTest {
    private String charsetName;
    private Path expectedPath;
    private Path srcPath;
    
    @Before
    public void setUp() {
        charsetName = "UTF-8";
        srcPath = get("fixtures/test/Page/index1.html");
        expectedPath = get("fixtures/expected/Blog/index.html");
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
    public void testCopyTo() throws IOException {
        Path destPath = get("fixtures/test/dest/Page/index1.html");
        Page srcPage = new Page(srcPath, charsetName);
        srcPage.copyTo(destPath);
        
        assertContentEquals(srcPath, destPath, charsetName);
    }
    
    @Test
    public void testCleanJs() throws IOException {
        Page resultPage = new Page(srcPath, charsetName);
        resultPage.cleanJs();
        
        assertContentEquals(expectedPath, srcPath, charsetName);
    }
}

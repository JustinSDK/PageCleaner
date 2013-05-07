package cc.openhome;

import static cc.openhome.TestHelper.assertPagesEquals;
import java.io.IOException;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import org.junit.Before;
import org.junit.Test;

public class PagesDirTest {
    private String charsetName;
    private Path expectedPagesDir;
    
    @Before
    public void setUp() {
        charsetName = "UTF-8";
        expectedPagesDir = get("fixtures/expected/Blog");
    }

    @Test
    public void testCleanPagesJs() throws IOException {
        Path srcDir = get("fixtures/test/PagesDir/Blog1");
        PagesDir pagesDir = new PagesDir(srcDir, charsetName);
        pagesDir.cleanPagesJs();
        
        assertPagesEquals(expectedPagesDir, srcDir, charsetName);
    }
    
    @Test
    public void testCopyTo() throws IOException {
        Path src = get("fixtures/test/PagesDir/Blog2");
        Path dest = get("fixtures/dest/PagesDir/Blog2");
        PagesDir srcDir = new PagesDir(src, charsetName);
        srcDir.copyTo(dest);
        assertPagesEquals(dest, src, charsetName);
    }
}

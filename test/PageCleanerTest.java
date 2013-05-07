import static cc.openhome.TestHelper.assertContentEquals;
import static cc.openhome.TestHelper.assertPagesEquals;
import static java.nio.file.Paths.get;
import org.junit.Before;
import org.junit.Test;
 
public class PageCleanerTest {
    private String expectedPagesDirName;
    private String expectedPathName;
    private String charsetName;
    
    @Before
    public void setUp() {
        expectedPagesDirName = "fixtures/expected/Blog";
        expectedPathName = "fixtures/expected/Blog/index.html";
        charsetName = "UTF-8";
    }
    
    @Test
    public void testMainFile() throws Exception {
        String srcFile1 = "fixtures/test/PageCleaner/index1.html";
        PageCleaner.main(new String[] {charsetName, srcFile1});
        
        assertContentEquals(get(expectedPathName), get(srcFile1), charsetName);
        
        String srcFile2 = "fixtures/test/PageCleaner/index2.html";
        String destFile2 = "fixtures/dest/PageCleaner/index2.html";
        PageCleaner.main(new String[] {charsetName, srcFile2, destFile2});
        
        assertContentEquals(get(expectedPathName), get(destFile2), charsetName);
    }
   
    @Test
    public void testMainDir() throws Exception {
        String srcDir1 = "fixtures/test/PageCleaner/Blog1";
        PageCleaner.main(new String[] {charsetName, srcDir1}); 
        assertPagesEquals(get(expectedPagesDirName), get(srcDir1), charsetName);
   
        String srcDir2 = "fixtures/test/PageCleaner/Blog2";
        String destDir2 = "fixtures/dest/PageCleaner/Blog2";
        PageCleaner.main(new String[] {charsetName, srcDir2, destDir2});        
        assertPagesEquals(get(expectedPagesDirName), get(destDir2), charsetName);
    }
}

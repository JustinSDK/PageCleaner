import static cc.openhome.TestHelper.assertRemoved;
import static cc.openhome.TestHelper.readAllText;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
 
public class JsRemoverTest {
    private String removedDir;
    private String removedFile;
    private String charsetName;
    
    @Before
    public void setUp() {
        removedDir = "fixtures/removed/Blog";
        removedFile = "fixtures/removed/Blog/index.html";
        charsetName = "UTF-8";
    }
    
    @Test
    public void testMainFile() throws Exception {
        String srcFile1 = "fixtures/test/JsRemover/index1.html";
        JsRemover.main(new String[] {charsetName, srcFile1});
        
        String result =  readAllText(Paths.get(srcFile1), charsetName);
        String expResult = readAllText(Paths.get(removedFile), charsetName);
        assertEquals(expResult, result);
        
        String srcFile2 = "fixtures/test/JsRemover/index2.html";
        String destFile2 = "fixtures/dest/JsRemover/index2.html";
        JsRemover.main(new String[] {charsetName, srcFile2, destFile2});
        
        result =  readAllText(Paths.get(destFile2), charsetName);
        expResult = readAllText(Paths.get(removedFile), charsetName);
        assertEquals(expResult, result);
    }
   
    @Test
    public void testMainDir() throws Exception {
        String srcDir1 = "fixtures/test/JsRemover/Blog1";
        JsRemover.main(new String[] {charsetName, srcDir1});     
        assertRemoved(Paths.get(srcDir1), "test/JsRemover/Blog1", "removed/Blog", charsetName);
   
        String srcDir2 = "fixtures/test/JsRemover/Blog2";
        String destDir2 = "fixtures/dest/JsRemover/Blog2";
        JsRemover.main(new String[] {charsetName, srcDir2, destDir2});        
        assertRemoved(Paths.get(destDir2), "dest/JsRemover/Blog2", "removed/Blog", charsetName);
    }
}

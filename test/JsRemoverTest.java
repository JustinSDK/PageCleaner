import static cc.openhome.TestHelper.assertRemoved;
import static cc.openhome.TestHelper.readAllText;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class JsRemoverTest {
    private String srcDir;
    private String destDir;
    private String removedDir;
    private String srcFile;
    private String destFile;
    private String removedFile;
    private String charsetName;
    
    @Before
    public void setUp() {
        srcDir = "fixtures/test/Blog";
        srcFile = srcDir + "/index.html";
        destDir = "fixtures/test/dest";
        destFile = destDir + "/index.html";
        removedDir = "fixtures/removed/Blog";
        removedFile = removedDir + "/index.html";
        charsetName = "UTF-8";
    }
    
    @Test
    public void testMainFile() throws Exception {
        JsRemover.main(new String[] {charsetName, srcFile});
        
        String result =  readAllText(Paths.get(srcFile), charsetName);
        String expResult = readAllText(Paths.get(removedFile), charsetName);
        assertEquals(expResult, result);
        
        JsRemover.main(new String[] {charsetName, srcFile, destFile});
        
        result =  readAllText(Paths.get(destFile), charsetName);
        expResult = readAllText(Paths.get(removedFile), charsetName);
        assertEquals(expResult, result);
    }
   
    @Test
    public void testMainDir() throws Exception {
        JsRemover.main(new String[] {charsetName, srcDir});     
        assertRemoved(Paths.get(srcDir), "test", "removed", charsetName);
   
        JsRemover.main(new String[] {charsetName, srcDir, destDir});        
        assertRemoved(Paths.get(destDir), "test/dest", "removed/Blog", charsetName);
    }
}

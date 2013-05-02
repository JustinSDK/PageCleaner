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
        srcDir = "fixtures/test/JavaScript";
        srcFile = srcDir + "/index.html";
        destDir = "fixtures/test/dest";
        destFile = destDir + "/index.html";
        removedDir = "fixtures/removed/JavaScript";
        removedFile = removedDir + "/index.html";
        charsetName = "UTF-8";
    }
    
    @Test
    public void testFileSrc() throws Exception {
        String[] args = {charsetName, srcFile};
        JsRemover.main(args);
        
        String result =  readAllText(Paths.get(srcFile), charsetName);
        String expResult = readAllText(Paths.get(removedFile), charsetName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFileSrcDest() throws Exception {
        String[] args = {charsetName, srcFile, destFile};
        JsRemover.main(args);
        
        String result =  readAllText(Paths.get(destFile), charsetName);
        String expResult = readAllText(Paths.get(removedFile), charsetName);
        assertEquals(expResult, result);
    }
   
    @Test
    public void testDirSrc() throws Exception {
        String[] args = {charsetName, srcDir};
        JsRemover.main(args);
        
        assertRemoved(Paths.get(srcDir), "test", "removed", charsetName);
    }
    
    @Test
    public void testDirSrcDest() throws Exception {
        String[] args = {charsetName, srcDir, destDir};
        JsRemover.main(args);
        
        assertRemoved(Paths.get(srcDir), "test/dest", "removed/JavaScript", charsetName);
    }
}

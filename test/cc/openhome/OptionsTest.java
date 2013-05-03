package cc.openhome;

import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class OptionsTest {
    @Test
    public void testConstructorWith2Args() {
        String[] args = {"UTF-8", "src"};
        Options options = new Options(args);
        
        assertEquals(options.charsetName, args[0]);
        assertEquals(options.src, Paths.get(args[1]));
        assertEquals(options.dest, Paths.get(args[1]));
    }
    
    @Test
    public void testConstructorWith3Args() {
        String[] args = {"UTF-8", "src", "dest"};
        
        Options options = new Options(args);
        
        assertEquals(options.charsetName, args[0]);
        assertEquals(options.src, Paths.get(args[1]));
        assertEquals(options.dest, Paths.get(args[2]));
    }
}

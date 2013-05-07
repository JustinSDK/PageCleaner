package cc.openhome;

import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.isRegularFile;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Args {
    public final String charsetName;
    public final Path src;
    public final Path dest;

    public Args(String[] args) {
        this.charsetName = args[0];
        this.src = Paths.get(args[1]);
        this.dest = Paths.get(args.length == 3 ? args[2] : args[1]);
    }
    
    public boolean isSrcRegularFile() {
        return isRegularFile(src);
    }
    
    public boolean isSrcDirectory() {
        return isDirectory(src);
    }
    
    public boolean hasDiffSrcDest() {
        return !src.equals(dest);
    }
}

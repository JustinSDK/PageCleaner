package cc.openhome;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import static java.nio.file.Files.*;
import java.nio.file.Path;

public class PagesDir {
    private Path dir;
    private String charsetName;

    public PagesDir(Path srcPath, String charsetName) {
        this.dir = srcPath;
        this.charsetName = charsetName;
    }

    public void cleanPagesJs() throws IOException {
        try(DirectoryStream<Path> pathes = newDirectoryStream(dir, "*.{htm,html}")) {
            for (Path path: pathes) {
                new Page(path, charsetName).cleanJs();
            }
        }
    }
    
    public static void copyRecursively(Path src, Path dest) throws IOException {
         createDirectories(dest);
         try(DirectoryStream<Path> pathes = newDirectoryStream(src)) {
            for (Path from: pathes) {
                Path to = dest.resolve(from.getFileName());
                if(isDirectory(from)) {
                    copyRecursively(from, to);
                } else {
                    copy(from, to);
                }
            }
        }
    }
    
    public void copyTo(Path dest) throws IOException {
        copyRecursively(dir, dest);
    }
    
}

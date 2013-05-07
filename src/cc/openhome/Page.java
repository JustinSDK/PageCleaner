package cc.openhome;

import java.io.IOException;
import static java.nio.file.Files.*;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;

public class Page {
    private static Pattern jsRegex = compile("<script *.*>(.*\\s*)*?</script>");
    
    public static String cleanHtmlJs(String html) {
        return jsRegex.matcher(html).replaceAll("");
    }
    
    private Path file;
    private String charsetName;
    
    public Page(Path path, String charsetName) {
        this.file = path;
        this.charsetName = charsetName;
    }

    public void copyTo(Path dest) throws IOException {
        createDirectories(dest.getParent());
        copy(file, dest);
    }
    
    public void cleanJs() throws IOException {
        String noJsHtml = cleanHtmlJs(readHtml());
        write(file, noJsHtml.getBytes(charsetName), TRUNCATE_EXISTING, CREATE);
    }
    
    public String readHtml() throws IOException {
        return new String(readAllBytes(file), charsetName);
    }
}

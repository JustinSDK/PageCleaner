package cc.openhome;

import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.*;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.Objects;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;

public class Page {
    private static Pattern jsRegex = compile("<script *.*>(.*\\s*)*?</script>");
    
    public static String cleanHtmlJs(String html) {
        return jsRegex.matcher(html).replaceAll("");
    }
    
    private Path path;
    private String charsetName;
    
    public Page(Path path, String charsetName) {
        this.path = path;
        this.charsetName = charsetName;
    }

    public void copyTo(Path dest) throws IOException {
        createDirectories(dest.getParent());
        copy(path, dest);
    }
    
    public void cleanJs() throws IOException {
        String noJsHtml = cleanHtmlJs(readHtml());
        write(path, noJsHtml.getBytes(charsetName), TRUNCATE_EXISTING, CREATE);
    }
    
    public String readHtml() throws IOException {
        return new String(Files.readAllBytes(path), charsetName);
    }

    public boolean htmlEquals(final Page other) throws RuntimeException {
        if (!Objects.equals(this.charsetName, other.charsetName)) {
            return false;
        }
        try {
            String html = readHtml();
            String otherHtml = other.readHtml();
            return html.equals(otherHtml);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

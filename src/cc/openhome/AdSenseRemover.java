package cc.openhome;

import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Files.write;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.util.regex.Pattern.compile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class AdSenseRemover {
	private static Pattern jsRegex = compile("<script *.*>(.*\\s*)*?</script>");
	
    public static void findPagesAndRemoveJs(Path file, String charsetName) throws IOException {
        try(DirectoryStream<Path> pages = newDirectoryStream(file, "*.{htm,html}")) {
            for (Path page: pages) { removeJs(page, charsetName); }
        }
    }
    
    public static void removeJs(Path file, String charsetName) throws IOException {
        String originalHtml = readAllText(file, charsetName);
        String noJsHtml = removeJs(originalHtml);
        overwrite(file, noJsHtml, charsetName);
    }
    
    public static String readAllText(Path file, String charsetName) throws IOException {
        return new String(readAllBytes(file), charsetName);
    }
    
    public static String removeJs(String html) {
        return jsRegex.matcher(html).replaceAll("");
    }
    
    public static void overwrite(Path file, String text, String charsetName) throws IOException {
        write(file, text.getBytes(charsetName), TRUNCATE_EXISTING);        
    }
}

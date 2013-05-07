package cc.openhome;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import static java.nio.file.Files.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;

public class AdSenseRemover {
    private static Pattern jsRegex = compile("<script *.*>(.*\\s*)*?</script>");

    public static void removeFileJs(Path file, String charsetName) throws IOException {
        String noJsHtml = readFileWithoutJs(file, charsetName);
        overwrite(file, noJsHtml, charsetName);
    }
        
    public static String readFileWithoutJs(Path file, String charsetName) throws IOException {
        String originalHtml = readAllText(file, charsetName);
        String noJsHtml = removeHtmlJs(originalHtml);
        return noJsHtml;
    }
    
    public static String removeHtmlJs(String html) {
        return jsRegex.matcher(html).replaceAll("");
    }
	
    public static void removeJsDir(Path dir, String charsetName) throws IOException {
        try(DirectoryStream<Path> pages = newDirectoryStream(dir, "*.{htm,html}")) {
            for (Path page: pages) { removeFileJs(page, charsetName); }
        }
    }
    
    public static void removeFileJsAndSaveAs(Path src, Path dest, String charsetName) throws IOException {
        String noJsHtml = readFileWithoutJs(src, charsetName);
        Files.createDirectories(dest.getParent());
        overwrite(dest, noJsHtml, charsetName);
    }
    
    public static void removeJsDirAndSaveAs(Path src, Path dest, String charsetName) throws IOException {
        Files.createDirectories(dest);
        try(DirectoryStream<Path> pages = newDirectoryStream(src, "*.{htm,html}")) {
            for (Path page: pages) { 
                String html = readFileWithoutJs(page, charsetName);
                Path destPage = Paths.get(dest.toString() + "\\" + page.getFileName());
                overwrite(destPage, html, charsetName);
            }
        }
    }
    
    private static String readAllText(Path file, String charsetName) throws IOException {
        return new String(readAllBytes(file), charsetName);
    }
    
    private static void overwrite(Path file, String text, String charsetName) throws IOException {
        write(file, text.getBytes(charsetName), TRUNCATE_EXISTING, CREATE);        
    }
}

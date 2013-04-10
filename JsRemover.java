import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.nio.file.DirectoryStream;
import static java.lang.System.out;
import static java.nio.file.Paths.get;
import static java.nio.file.Files.*;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.util.regex.Pattern.compile;

public class JsRemover {
    private static Pattern jsRegex = compile("<script *.*>(.*\\s*)*?</script>");
    
    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            out.println(
                "Usage: java JsRemover [FileName | DirName] (encoding)\n" +
                "Examples:\n" +
                "\tRemove JavaScript from a html page:\n" + 
                "\t\tjava JsRemover index.html\n" +
                "\t\tjava JsRemover index.html UTF-8\n" +
                "\t\tjava JsRemover JavaScript/index.html UTF-8\n\n" +
                "\tRemove JavaScript from all pages in a directory:\n" + 
                "\t\tjava JsRemover JavaScript\n" +
                "\t\tjava JsRemover JavaScript UTF-8\n"
            );
        } else {
            String fileName = args[0];
            String charsetName = args.length == 2 ? args[1] : System.getProperty("file.encoding");
        
            Path file = get(fileName);
            if(isDirectory(file)) { findPagesAndRemoveJs(file, charsetName); } 
            else { removeJs(file, charsetName); }
        }
    }
    
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
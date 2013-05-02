
import static cc.openhome.AdSenseRemover.*;
import java.io.IOException;
import static java.lang.System.out;
import static java.nio.file.Files.isDirectory;
import java.nio.file.Path;
import static java.nio.file.Paths.get;

public class JsRemover {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            prompt();
        } else {
            String charsetName = args[0];
            String srcName = args[1];
            if(args.length == 2) {
                removeAdSenseJs(srcName, charsetName);
            } else {
                String destName = args[2];
                removeAdSenseJsAndSaveAs(srcName, destName, charsetName);
            }
        }
    }

    private static void removeAdSenseJs(String srcName, String charsetName)
            throws IOException {
        Path file = get(srcName);
        if (isDirectory(file)) {
            removeJsDir(file, charsetName);
        } else {
            removeFileJs(file, charsetName);
        }
    }
    
    private static void removeAdSenseJsAndSaveAs(String srcName, String destName, String charsetName)
            throws IOException {
        Path src = get(srcName);
        Path dest = get(destName);
        if (isDirectory(src)) {
            removeJsDirAndSaveAs(src, dest, charsetName);
        } else {
            removeFileJsAndSaveAs(src, dest, charsetName);
        }
    }

    private static void prompt() {
        out.println(
                "Usage: java JsRemover encoding [srcFile | srcDir] (destFile | destDir)\n"
                + "Examples:\n"
                + "\tRemove JavaScript from a html page:\n"
                + "\t\tjava JsRemover UTF-8 index.html\n"
                + "\t\tjava JsRemover UTF-8 index.html\n"
                + "\t\tjava JsRemover UTF-8 JavaScript/index.html\n\n"
                + "\tRemove JavaScript from all pages in a directory:\n"
                + "\t\tjava JsRemover UTF-8 JavaScript\n"
                + "\t\tjava JsRemover UTF-8 JavaScript\n");
    }
}
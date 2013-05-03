
import static cc.openhome.AdSenseRemover.*;
import cc.openhome.Options;
import java.io.IOException;
import static java.lang.System.out;
import static java.nio.file.Files.isDirectory;

public class JsRemover {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            prompt();
        } else {
            removeAdSense(new Options(args));
        }
    }

    private static void prompt() {
        out.println(
                "Usage: java JsRemover encoding [srcFile | srcDir] (destFile | destDir)\n"
                + "Examples:\n"
                + "\tRemove JavaScript from a html page:\n"
                + "\t\tjava JsRemover UTF-8 index.html\n"
                + "\t\tjava JsRemover UTF-8 JavaScript/index.html dest/index.html\n\n"
                + "\tRemove JavaScript from all pages in a directory:\n"
                + "\t\tjava JsRemover UTF-8 JavaScript\n"
                + "\t\tjava JsRemover UTF-8 JavaScript dest\n");
    }

    public static void removeAdSense(Options options) throws IOException {
        if(options.src.equals(options.dest)) {
            removeJsAndSaveBack(options);
        } else {
            removeAdSenseJsAndSaveAs(options);
        }
    }
    
    public static void removeJsAndSaveBack(Options options)
            throws IOException {
        if (isDirectory(options.src)) {
            removeJsDir(options.src, options.charsetName);
        } else {
            removeFileJs(options.src, options.charsetName);
        }
    }
    
    public static void removeAdSenseJsAndSaveAs(Options options)
            throws IOException {
        if (isDirectory(options.src)) {
            removeJsDirAndSaveAs(options.src, options.dest, options.charsetName);
        } else {
            removeFileJsAndSaveAs(options.src, options.dest, options.charsetName);
        }
    }
}
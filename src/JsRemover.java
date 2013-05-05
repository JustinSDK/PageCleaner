
import static cc.openhome.AdSenseRemover.*;
import cc.openhome.Args;
import java.io.IOException;
import static java.lang.System.out;
import static java.nio.file.Files.isDirectory;

public class JsRemover {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            prompt();
        } else {
            removeAdSense(new Args(args));
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

    public static void removeAdSense(Args args) throws IOException {
        if(args.hasSameSrcDest()) {
            removeJsAndSaveBack(args);
        } else {
            removeAdSenseJsAndSaveAs(args);
        }
    }
    
    public static void removeJsAndSaveBack(Args args)
            throws IOException {
        if (args.isSrcDirectory()) {
            removeJsDir(args.src, args.charsetName);
        } else if(args.isSrcRegularFile()) {
            removeFileJs(args.src, args.charsetName);
        }
    }
    
    public static void removeAdSenseJsAndSaveAs(Args args)
            throws IOException {
        if (args.isSrcDirectory()) {
            removeJsDirAndSaveAs(args.src, args.dest, args.charsetName);
        } else if (args.isSrcRegularFile()) {
            removeFileJsAndSaveAs(args.src, args.dest, args.charsetName);
        }
    }
}
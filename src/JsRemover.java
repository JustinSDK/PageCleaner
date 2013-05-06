
import static cc.openhome.AdSenseRemover.removeJsDir;
import static cc.openhome.AdSenseRemover.removeJsDirAndSaveAs;
import cc.openhome.Args;
import cc.openhome.Page;
import java.io.IOException;
import static java.lang.System.out;

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
            new Page(args.src, args.charsetName).cleanJs();
        }
    }
    
    public static void removeAdSenseJsAndSaveAs(Args args)
            throws IOException {
        if (args.isSrcDirectory()) {
            removeJsDirAndSaveAs(args.src, args.dest, args.charsetName);
        } else if (args.isSrcRegularFile()) {
            new Page(args.src, args.charsetName).copyTo(args.dest);
            new Page(args.dest, args.charsetName).cleanJs();
        }
    }
}
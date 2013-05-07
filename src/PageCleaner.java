import cc.openhome.Args;
import cc.openhome.Page;
import cc.openhome.PagesDir;
import java.io.IOException;
import static java.lang.System.out;

public class PageCleaner {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            prompt();
        } else {
            clean(new Args(args));
        }
    }

    private static void prompt() {
        out.println(
                "Usage: java PageCleaner encoding [srcFile | srcDir] (destFile | destDir)\n"
                + "Examples:\n"
                + "\tRemove JavaScript from a html page:\n"
                + "\t\tjava PageCleaner UTF-8 index.html\n"
                + "\t\tjava PageCleaner UTF-8 JavaScript/index.html dest/index.html\n\n"
                + "\tRemove JavaScript from all pages in a directory:\n"
                + "\t\tjava PageCleaner UTF-8 JavaScript\n"
                + "\t\tjava PageCleaner UTF-8 JavaScript dest\n");
    }

    private static void clean(Args args) throws IOException {
        if (args.isSrcDirectory()) {
            cleanPagesDir(args);
        } else if(args.isSrcRegularFile()) {
            cleanPage(args);
        }
    }
    
    private static void cleanPage(Args args) throws IOException {
        if(args.hasDiffSrcDest()) {
            new Page(args.src, args.charsetName).copyTo(args.dest);
        }
        new Page(args.dest, args.charsetName).cleanJs();
    }

    private static void cleanPagesDir(Args args) throws IOException {
        if(args.hasDiffSrcDest()) {
            new PagesDir(args.src, args.charsetName).copyTo(args.dest);
        }
        new PagesDir(args.dest, args.charsetName).cleanPagesJs();
    }
}
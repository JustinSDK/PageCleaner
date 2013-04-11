import java.io.IOException;
import java.nio.file.Path;
import static java.lang.System.out;
import static java.nio.file.Paths.get;
import static java.nio.file.Files.*;
import static cc.openhome.AdSenseRemover.*;

public class JsRemover {
    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            prompt();
        } else {
            String fileName = args[0];
            String charsetName = args.length == 2 ? args[1] : System.getProperty("file.encoding");
            removeAdSenseJs(fileName, charsetName);
        }
    }

	private static void removeAdSenseJs(String fileName, String charsetName)
			throws IOException {
		Path file = get(fileName);
		if(isDirectory(file)) { removeJsFromPagesUnder(file, charsetName); } 
		else { removeJs(file, charsetName); }
	}

	private static void prompt() {
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
	}
}
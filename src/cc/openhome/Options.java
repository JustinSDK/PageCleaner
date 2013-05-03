package cc.openhome;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Options {
    public final String charsetName;
    public final Path src;
    public final Path dest;

    public Options(String[] args) {
        this.charsetName = args[0];
        this.src = Paths.get(args[1]);
        this.dest = Paths.get(args.length == 3 ? args[2] : args[1]);
    }
}

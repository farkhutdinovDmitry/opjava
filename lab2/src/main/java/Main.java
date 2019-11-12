import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        FileFinder fileFinder = new FileFinder("foo", Paths.get(System.getProperty("user.dir")));
        List<Path> list = fileFinder.getFiles();
        DataFinder dataFinder = new DataFinder(list, "moo");
        dataFinder.find();
    }

}

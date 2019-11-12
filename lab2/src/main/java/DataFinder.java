import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DataFinder {
    private final static int BUFFER_SIZE = 10000;
    private List<Path> haystack;
    private String needle;
    private char[] buffer;

    public DataFinder(List<Path> haystack, String needle) {
        this.haystack = haystack;
        this.needle = needle;
        buffer = new char[BUFFER_SIZE];
    }

    public void find() throws IOException {
        for (Path path : haystack) {
            FileReader fileReader = new FileReader(path.toFile());
            BufferedReader reader = new BufferedReader(fileReader);
            int read = reader.read(buffer);
            BoyerMoore boyerMoore = new BoyerMoore(needle);
            while (read != -1) {
                if (boyerMoore.find(buffer)) {
                    System.out.println("Found in " + path);
                    break;
                }
                read = reader.read(buffer);
            }
        }

    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataFinder {
    private final static int BUFFER_SIZE = 4000;
    private List<Path> haystack;
    private String needle;

    public DataFinder(List<Path> haystack, String needle) {
        this.haystack = haystack;
        this.needle = needle;
    }

    public List<Path> find() throws IOException {
        List<Path> containers = new ArrayList<>();
        for (Path path : haystack) {
            CharBuffer buffer = CharBuffer.allocate(BUFFER_SIZE < needle.length() ? BUFFER_SIZE + needle.length() : BUFFER_SIZE);
            FileReader fileReader = new FileReader(path.toFile());
            BufferedReader reader = new BufferedReader(fileReader);
            StringSearcher stringSearcher = new StringSearcher(needle);
            int read = reader.read(buffer);
            while (read != -1) {
                if (stringSearcher.find(buffer.array())) {
                    containers.add(path);
                    break;
                }
                buffer.clear();
                read = reader.read(buffer);
            }
        }
        return containers;
    }
}

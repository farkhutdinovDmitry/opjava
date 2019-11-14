import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private String fileName;
    private Path start;
    private List<Path> founds;

    public FileFinder(String fileName, Path start) {
        this.fileName = fileName;
        this.start = start;
        founds = new ArrayList<>();
    }

    public List<Path> getFiles() {
        try {
            Files.walkFileTree(start, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
                    if (fileName == null) {
                        founds.add(path);
                        return FileVisitResult.CONTINUE;
                    }
                    if (getFileNameOnly(path).equals(fileName)) {
                        founds.add(path);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return founds;
    }

    private String getFileNameOnly(Path path) {
        String fileName = path.toFile().getName();
        int index = fileName.lastIndexOf(".");
        return index > 0 ? fileName.substring(0, index) : fileName;
    }
}

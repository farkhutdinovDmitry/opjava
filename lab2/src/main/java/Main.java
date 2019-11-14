import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String fileName = null, data = null, startingDirectory = null;
        CommandLine commandLine = parseArguments(args);
        if (commandLine.hasOption("h")) {
            System.out.println("Usage: java -jar ./app.jar [--name <fileName>] [--data '<text>'] <folder|file>");
            return;
        }
        if (commandLine.hasOption("name")) {
            String[] fileArgs = commandLine.getOptionValues("name");
            fileName = fileArgs[0];
            if (fileArgs.length > 1) {
                startingDirectory = fileArgs[1];
            }
        }
        if (commandLine.hasOption("data")) {
            String[] dataArgs = commandLine.getOptionValues("data");
            data = dataArgs[0];
            if (dataArgs.length > 1 && startingDirectory == null) {
                startingDirectory = dataArgs[1];
            } else {
                throw new IllegalArgumentException("bad input");
            }
        }
        if (startingDirectory == null || (fileName == null && data == null)) {
            throw new IllegalArgumentException("bad input");
        }
        FileFinder fileFinder = new FileFinder(fileName, Paths.get(startingDirectory));
        List<Path> list = fileFinder.getFiles();
        if (data != null) {
            DataFinder dataFinder = new DataFinder(list, data);
            list = dataFinder.find();
        }
        for (Path path : list) {
            System.out.println(path.toString());
        }
    }

    private static CommandLine parseArguments(String[] args) throws ParseException {
        Options options = new Options();
        Option fileOption = new Option("name", true, "filename");
        fileOption.setArgs(2);
        Option dataOption = new Option("data", true, "data to search in files");
        dataOption.setArgs(2);
        Option helpOption = new Option("h", false, "help");
        options.addOption(fileOption);
        options.addOption(dataOption);
        options.addOption(helpOption);
        CommandLineParser lineParser = new GnuParser();
        return lineParser.parse(options, args);
    }
}

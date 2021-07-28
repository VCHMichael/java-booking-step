package app.logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class Logger {
    private static final String LOG_FILE_PATH = "src/main/resources/log.txt";
    private final Class clazz;

    public Logger(Class clazz) {
        File log = new File(LOG_FILE_PATH);
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clazz = clazz;
    }

    public void info(String message) {
        String decoratedMessage = "INFO: " + new Date() + " [" + clazz.getSimpleName() + "] - " + message + "\n";
        try {
            Files.write(Paths.get(LOG_FILE_PATH), decoratedMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void error(String message, Throwable e) {
        String stackTrace = Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(" \n"));
        String decoratedMessage = "ERROR: " + new Date() + " [" + clazz.getSimpleName() + "] - " + message + "\n" + stackTrace + "\n";
        try {
            Files.write(Paths.get(LOG_FILE_PATH), decoratedMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void error(String message) {
        String decoratedMessage = "ERROR: " + new Date() + " [" + clazz.getSimpleName() + "] - " + message + "\n";
        try {
            Files.write(Paths.get(LOG_FILE_PATH), decoratedMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

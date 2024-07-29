package org.example.support;

public class FileReaderManager {

    private static final FileReaderManager fileReaderManager = new FileReaderManager();
    private static ReadConfig configFileReader;

    private FileReaderManager() {}

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ReadConfig getConfigFileReader() {
        return (configFileReader == null) ? new ReadConfig() : configFileReader;
    }
}

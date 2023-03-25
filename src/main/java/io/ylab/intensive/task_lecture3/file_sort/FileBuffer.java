package io.ylab.intensive.task_lecture3.file_sort;

import java.io.*;

public class FileBuffer {
    public static int BUFFER_SIZE = 2048;
    public BufferedReader buffReader;
    public File originalfile;
    private String cache;
    private boolean empty;

    public FileBuffer(File file) throws IOException {
        originalfile = file;
        buffReader = new BufferedReader(new FileReader(file), BUFFER_SIZE);
        reload();
    }

    public boolean empty() {
        return empty;
    }

    private void reload() throws IOException {
        try {
            empty = (this.cache = buffReader.readLine()) == null;
        } catch (EOFException eof) {
            empty = true;
            cache = null;
        }
    }

    public void close() throws IOException {
        buffReader.close();
    }

    public String peek() {
        if (empty()) {
            return null;
        }
        return cache;
    }

    public String pop() throws IOException {
        String answer = peek();
        reload();
        return answer;
    }
}

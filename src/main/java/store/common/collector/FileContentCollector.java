package store.common.collector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.constant.FilePath;

public abstract class FileContentCollector<T> {

    private final FilePath filePath;
    private long sequence = 0L;

    public FileContentCollector(FilePath filePath) {
        this.filePath = filePath;
    }

    public final List<T> collect() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.get()));
        bufferedReader.readLine();
        List<T> result = executeCollect(bufferedReader);

        bufferedReader.close();
        return result;
    }

    private List<T> executeCollect(BufferedReader bufferedReader) throws IOException {
        List<T> instances = new ArrayList<>();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            instances.add(toInstance(line, sequence++));
        }
        return instances;
    }

    protected abstract T toInstance(String line, long sequence);
}

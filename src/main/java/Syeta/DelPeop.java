package Syeta;

import java.io.File;

public class DelPeop {
    private final File file;
    private final long length;
    private final String lastModified;

    public String getLastModified() {
        return lastModified;
    }

    public File getFile() {
        return file;
    }

    public long getLength() {
        return length;
    }

    public DelPeop(File file, long length, String lastModified) {
        this.file = file;
        this.length = length;
        this.lastModified = lastModified;
    }
}

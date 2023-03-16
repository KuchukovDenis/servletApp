package Syeta;

import Syeta.DelPeop;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class ExplorerService {
    public File getUserFiles(String login, String path) {
        if (path == null) {
            path = "";
        }
        if (path.contains("/..")){
            path = path.replace("/..","");
        }
        File file = getUserRootFolder(login);
        path = path.startsWith("/" + login + "/") ? path.split(login + "/")[1] : "";
        return new File(file.getAbsolutePath() + "\\" + path);
    }

    private File getUserRootFolder(String login) {
        File root = new File("C:\\PeopleReg");
        boolean isFolderExist = false;
        for (File file : root.listFiles()) {
            if (file.isDirectory() && file.getName().equals(login)) {
                isFolderExist = true;
            }
        }

        if (!isFolderExist) {
            File file = new File(root.getAbsolutePath() + "\\" + login);
            file.mkdir();
        }

        return new File(root.getAbsolutePath() + "\\" + login);
    }

    public DelPeop[] getSubDirectory(File file) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy ss:mm:hh a");
        return Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(File::isDirectory)
                .map(x -> new DelPeop(x, x.length(), format.format(new Date(x.lastModified()))))
                .toArray(DelPeop[]::new);
    }

    public DelPeop[] getSubFile(File file) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy ss:mm:hh a");
        return Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(File::isFile)
                .map(x -> new DelPeop(x, x.length(), format.format(new Date(x.lastModified()))))
                .toArray(DelPeop[]::new);
    }
}
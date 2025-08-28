package Practice1.compositeDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:<br>
 * Date: 23/10/24-11:39 pm
 *
 * @author ishangarg
 * @since
 */

public class Directory implements FileSystem {
    String directoryName;
    List<FileSystem> fileSystemList;

    public Directory(String name) {
        this.directoryName = name;
        fileSystemList = new ArrayList<>();
    }

    public void add(FileSystem fileSystemObj) {
        fileSystemList.add(fileSystemObj);
    }

    public void ls() {
        System.out.println("Directory name " + directoryName);

        for (FileSystem fileSystemObj : fileSystemList) {
            fileSystemObj.ls();
        }
    }
}

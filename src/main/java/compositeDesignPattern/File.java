package compositeDesignPattern;

/**
 * Description:<br>
 * Date: 23/10/24-11:45 pm
 *
 * @author ishangarg
 * @since
 */

public class File implements  FileSystem{

    String fileName;

    public File(String name){
        this.fileName=name;
    }

    @Override
    public void ls() {
      System.out.println("file name "+fileName);
    }
}

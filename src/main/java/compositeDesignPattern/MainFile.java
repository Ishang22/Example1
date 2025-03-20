package compositeDesignPattern;

/**
 * Description:<br>
 * Date: 23/10/24-11:49 pm
 *
 * @author ishangarg
 * @since
 */
public class MainFile {

    public static void main(String[] args){
        Directory movieDirectory = new Directory("Movie");

        FileSystem border = new File("Border");

        movieDirectory.add(border);

        Directory comedyMovieDirectory = new Directory("ComedyMovie");
        File hulchul = new File("Hulchul");

        comedyMovieDirectory.add(hulchul);

        movieDirectory.add(comedyMovieDirectory);


        movieDirectory.ls();

    }

}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileContent {

    public static String getFileContent(String path) throws IOException {
        //Getting the content of the file
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String content = new String(bytes);
//        System.out.println("File content is : \n" + content);
        return content;
    }
}

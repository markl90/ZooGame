import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


public class unitTest {


    @Test
    public void addAnimal(){
        String source = "add";
        Main main = new Main(new Scanner(source));
        String data = "add";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        System.setIn(stdin);

    }
}

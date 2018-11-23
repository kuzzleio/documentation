import io.kuzzle.sdk.*;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CodeExampleGenericClass {
    private static Kuzzle kuzzle;

    public static void main(String[] args) {
      kuzzle = new Kuzzle("kuzzle");
      kuzzle.connect();

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(baos);
      PrintStream cout_original = System.out;

      System.setOut(ps);

      [snippet-code]

      while (baos.size() == 0) {
        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          System.err.println(e.getMessage());
        }
      }

      System.out.flush();
      System.setOut(cout_original);
      System.out.println(baos.toString());
    }
}
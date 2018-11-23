import io.kuzzle.sdk.*;
import java.util.Date;

public class CodeExampleGenericClass {
    private static Kuzzle kuzzle;

    public static void main(String[] args) {
      kuzzle = new Kuzzle("kuzzle");
      kuzzle.connect();
      [snippet-code]
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.err.println(e.getMessage());
      }
    }
}

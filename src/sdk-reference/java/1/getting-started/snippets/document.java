import io.kuzzle.sdk.*;

public class gettingstartedstorage {
    private static Kuzzle kuzzle;

    public static void main(String[] args) {
        // Creates a WebSocket connection.
        // Replace "kuzzle" with
        // your Kuzzle hostname like "localhost"
        WebSocket ws = new WebSocket("kuzzle");
        // Instantiates a Kuzzle client
        Kuzzle kuzzle = new Kuzzle(ws, null);

        // Connects to the server.
        try {
            kuzzle.connect();
            System.out.println("Connected!");
        } catch(KuzzleException e){
            System.err.println(e.getMessage());
        }

        // New document content
        String content = "{"
                        + "\"name\": \"Sirkis\","
                        + "\"birthday\": \"1959-06-22\","
                        + "\"license\": \"B\""
                        + "}";

        // Stores the document in the "yellow-taxi" collection.
        try {
            kuzzle.getDocument()
                  .create( "nyc-open-data", "yellow-taxi", "some-id", content);
            System.out.println("New document added to the yellow-taxi collection!");
        } catch(KuzzleException e){
            System.err.println(e.getMessage());
        }

        // Disconnects the SDK.
        kuzzle.disconnect();
    }
}

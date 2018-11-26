import io.kuzzle.sdk.*;

public class gettingstartedfirstconnection {
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

        // Freshly installed Kuzzle servers are empty: we need to create
        // a new index.
        try {
            kuzzle.getIndex().create("nyc-open-data");
            System.out.println("Index nyc-open-data created!");
        } catch(KuzzleException e){
            System.err.println(e.getMessage());
        }

        // Creates a collection
        try {
            kuzzle.getCollection().create("nyc-open-data", "yellow-taxi");
            System.out.println("Collection yellow-taxi created!");
        } catch(KuzzleException e){
            System.err.println(e.getMessage());
        }

        // Disconnects the SDK
        kuzzle.disconnect();
    }
}

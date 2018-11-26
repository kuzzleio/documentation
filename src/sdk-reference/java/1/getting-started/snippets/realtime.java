import io.kuzzle.sdk.*;

public class gettingstartedrealtime {
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

        // Starts an async listener
        NotificationListener listener = new NotificationListener() {
          public void onMessage(NotificationResult notification) {
            String content = notification.getResult().getContent();
            System.out.println("New created document notification: " + content);
          }
        };

        // Subscribes to notifications for drivers having a "B" driver license.
        String filters = new String("{ \"equals\": { \"license\":\"B\" } }");

        try {
            // Sends the subscription
            kuzzle.getRealtime()
                  .subscribe( "nyc-open-data", "yellow-taxi", filters, listener);
            System.out.println("Successfully subscribed!");
        } catch(KuzzleException e){
            System.err.println(e.getMessage());
        }

        // Writes a new document. This triggers a notification
        // sent to our subscription.
        String content =  "{"
                         + "\"name\": \"John\","
                         + "\"birthday\": \"1995-11-27\","
                         + "\"license\": \"B\""
                         + "}";

        try {
            kuzzle.getDocument()
                  .create( "nyc-open-data", "yellow-taxi", "", content);
            System.out.println("New document added to the yellow-taxi collection!");
        } catch(KuzzleException e){
            System.err.println(e.getMessage());
        }

        // Disconnects the SDK.
        kuzzle.disconnect();
    }
}

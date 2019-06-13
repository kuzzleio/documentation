
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;

Options options = new Options();

options.setDefaultIndex("some index")
  .setAutoReconnect(true),
  .setHeaders(new JSONObject().put("someheader", "value"))
  .setPort(7512);

Kuzzle kuzzle = new Kuzzle("localhost", options, new ResponseListener<Void>() {
 @Override
 public void onSuccess(Void object) {
   // invoked once connected, object contains the kuzzle instance
 }

 @Override
 public void onError(JSONObject error) {
   // Handle connection error
 }
});

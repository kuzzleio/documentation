
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options; 

Kuzzle kuzzle = new Kuzzle("localhost");

JSONObject filters = new JSONObject()
   .put("match_all", new JSONObject()
       .put("boost", 1)
   );

Options options = new Options();
options.setFrom((long) 0);
options.setSize((long) 20);

kuzzle
  .collection("collection", "index")
  .searchSpecifications(filters, options, new ResponseListener<JSONObject>() {
    @Override
    public void onSuccess(JSONObject res) {
      for (int i = 0; i < res.getJSONArray("hits").length(); i++) {
        res.getJSONArray("hits").getJSONObject(i) // Specification
      }

      res.getString("total"); // Total specifications count
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });


Options opts = new Options();
opts.setScroll("1m");

kuzzle
  .collection("collection", "index")
  .scrollSpecifications(scrollId, opts, new ResponseListener<JSONObject>() {
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

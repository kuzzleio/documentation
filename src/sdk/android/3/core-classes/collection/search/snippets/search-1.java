
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options; 

Kuzzle kuzzle = new Kuzzle("localhost");

JSONObject body = new JSONObject()
  .put("query", new JSONObject()
    .put("bool", new JSONObject()
      .put("must", new JSONArray()
        .put(
          new JSONObject().put("terms",
            new JSONObject().put("status",
              new JSONArray()
                .put("idle")
                .put("wantToHire")
                .put("toHire")
                .put("riding")
            )
          )
        )
        .put(
          new JSONObject().put("term",
            new JSONObject()
              .put("type", new JSONArray().put("cab"))
          )
        )
        .put(
          new JSONObject().put("geo_distance",
            new JSONObject()
              .put("distance", "10km")
              .put("pos",
                new JSONObject()
                  .put("lat", "48.8566140")
                  .put("lon", "2.352222")
              )
          )
        )
      )
    )
  )
  .put("sort", new JSONArray()
    .put("status")
    .put(new JSONObject()
      .put("_geo_distance", new JSONObject()
        .put("pos", new JSONObject()
          .put("lat", "48.8566140")
          .put("lon", "2.352222")
        )
        .put('order'; "asc")
      )
    )
    .put(new JSONObject()
      .put("date", "desc")
    )
  )
  .put("aggregations", new JSONObject()
    .put("aggs_name", new JSONObject()
      .put("terms", new JSONObject()
        .put("field", "field_name")
      )
    )
  );

Options options = new Options();
options.setFrom((long) 0);
options.setSize((long) 20);

kuzzle
  .collection("collection", "index")
  .search(body, options, new ResponseListener<SearchResult>() {
    @Override
    public void onSuccess(SearchResult searchResult) {
      for (Document doc : searchResult.getDocuments()) {
        // Get documents
      }

      searchResult.getTotal(); // return total of documents returned

      searchResult.getAggregations(): // return a JSONObject representing the aggregations response
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });

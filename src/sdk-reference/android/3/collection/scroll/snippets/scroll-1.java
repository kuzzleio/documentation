
Options opts = new Options();
opts.setScroll("1m");

kuzzle
  .collection("collection", "index")
  .scroll(scrollId, opts, new ResponseListener<SearchResult>() {
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

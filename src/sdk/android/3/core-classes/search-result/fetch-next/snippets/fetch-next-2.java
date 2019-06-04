
import io.kuzzle.sdk.core.Kuzzle;
import io.kuzzle.sdk.core.Options;

Kuzzle kuzzle = new Kuzzle("localhost");

JSONObject filter = new JSONObject();

Options options = new Options();
options.setFrom((long) 0);
options.setSize((long) 1000);
options.setScroll("30s");

ResponseListener<SearchResult> listener = new ResponseListener<SearchResult>() {
  @Override
  public void onSuccess(SearchResult searchResult) {
    if (searchResult == null) {
      return;
    }

    for (Document doc : searchResult.getDocuments()) {
      // do something with the document
      // this.processDocument(doc);
    }

    searchResult.fetchNext(this);
  }

  @Override
  public void onError(JSONObject error) {
    // handle errors here
  }
};

kuzzle
  .collection("collection", "index")
  .search(filter, options, listener);

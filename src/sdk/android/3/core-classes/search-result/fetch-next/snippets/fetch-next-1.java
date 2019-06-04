
searchResult.fetchNext(new ResponseListener<SearchResult>() {
  @Override
  public void onSuccess(SearchResult nextSearchResult) {
    // called once the fetchNext action has been completed
    // nextSearchResult is an instantiated SearchResult object
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
});

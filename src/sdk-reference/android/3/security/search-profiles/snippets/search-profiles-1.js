
// optional: search only for profiles referring the listed roles
JSONObject filters = new JSONObject()
  .put("roles", new JSONArray().put("myrole").put("admin"));

// optional: result pagination configuration
Options options = new Options();
options.setFrom((long) 0);
options.setSize((long) 42);
options.setScroll("1m");


kuzzle
  .security
  .searchProfiles(filters, options, new ResponseListener<SecurityDocumentList>() {
    @Override
    public void onSuccess(SecurityDocumentList profiles) {
      // Contains a profiles list
      for(Profile profile : profiles.getDocuments()) {

      }

      // Total number of profiles, regardless of pagination
      long total = profiles.getTotal();

      // Available only if a "scroll" option has been provided
      String scrollId = profiles.getScroll()
    }

    @Override
    public void onError(JSONObject error) {

    }
  });


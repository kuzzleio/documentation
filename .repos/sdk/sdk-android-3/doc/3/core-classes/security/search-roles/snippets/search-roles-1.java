
// optional: retrieve only roles allowing access to the
// provided controller names
JSONObject filter = new JSONObject()
  .put("controllers", new JSONArray()
    .put("document")
    .put("security")
  );

// optional: result pagination configuration
Options options = new Options();
options.setFrom((long) 0);
options.setSize((long) 42);
options.setScroll("1m");

kuzzle
  .security
  .searchRoles(filter, options, new ResponseListener<SecurityDocumentList>() {
    @Override
    public void onSuccess(SecurityDocumentList roles) {
      // roles.getDocuments() returns a roles list
      for(Role role : roles.getDocuments()) {

      }

      // roles.getTotal() returns the number of matched roles, regardless of pagination
      roles.getTotal();
    }
  });

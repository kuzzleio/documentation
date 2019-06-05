
JSONObject filter = new JSONObject()
  .put("bool", new JSONObject()
    .put("must", new JSONArray()
      .put(new JSONObject()
        .put("terms", new JSONObject()
          .put("profileIds", new JSONArray().put("anonymous").put("default"))
        )
      )
      .put(new JSONObject()
        .put("geo_distance", new JSONObject()
          .put("distance", "10km")
          .put("pos", new JSONObject()
            .put("lat", 48.8566140)
            .put("lon", 2.352222)
          )
        )
      )
    )
  );

// optional: result pagination configuration
Options options = new Options();
options.setFrom((long) 0);
options.setSize((long) 42);
options.setScroll("1m");

kuzzle
  .security
  .searchUsers(filters, options, new ResponseListener<SecurityDocumentList>() {
    @Override
    public void onSuccess(SecurityDocumentList users) {
      // users.getDocuments() returns an users list
      for(User user : users.getDocuments()) {

      }

      // Total number of profiles, regardless of pagination
      long total = users.getTotal();

      // Available only if a "scroll" option has been provided
      String scrollId = users.getScroll()
    }

    @Override
    public void onError(JSONObject error) {

    }
  });

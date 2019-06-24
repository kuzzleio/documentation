
JSONObject filter = new JSONObject()
  .put("and", new JSONArray()
    .put(
      new JSONObject().put("in",
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
      new JSONObject().put("in",
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
  );

kuzzle
  .collection("collection", "index")
  .subscribe(filter, new ResponseListener<NotificationResponse>() {
    @Override
    public void onSuccess(NotificationResponse object) {
      // called each time a new notification on this filter is received

      // check the Room/Notifications section of this documentation
      // to get notification examples
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  })
  .onDone(new ResponseListener<Room>() {
    @Override
    public void onSuccess(Room response) {
      // Handle subscription success
    }

    @Override
    public void onError(JSONObject error) {
      // Handle subscription error
    }
  });

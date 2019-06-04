
JSONObject filters = new JSONObject()
  .put("in",
    new JSONObject("field")
      .put(new JSONArray()
        .put("some")
        .put("filter")
      )
  );

Room room = kuzzle.collection("collection", "index")
  .room()
  .renew(filters, new ResponseListener<NotificationResponse>() {
    @Override
      public void onSuccess(NotificationResponse object) {
        // handle notifications
      }

      @Override
      public void onError(JSONObject error) {
        // Handle notifications error
      }
  });

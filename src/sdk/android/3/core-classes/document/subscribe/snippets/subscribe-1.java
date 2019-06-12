
Room room = document.subscribe(new ResponseListener<NotificationResponse>() {
    @Override
    public void onSuccess(NotificationResponse object) {
      // called each time a change occurs on this document
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

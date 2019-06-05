
document.exists(new ResponseListener<Boolean>() {
    @Override
    public void onSuccess(Boolean exists) {
      // called once the exists check has been completed
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
});

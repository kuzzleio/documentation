
document.save(new ResponseListener<Document>() {
    @Override
    public void onSuccess(Document object) {
      // called once the save action has been completed
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
});

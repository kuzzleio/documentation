
document.delete(new ResponseListener<Document>() {
    @Override
    public void onSuccess(Document object) {
      // called once the delete action has been completed
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
});

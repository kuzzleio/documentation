
Document myDocument = new Document(collection);
myDocument.setContent("title", "foo");
myDocument.setContent("content", "bar");

Options opts = new Options();
opts.setIfExist = "replace";

kuzzle
  .collection("collection", "index")
  .createDocument(myDocument, new ResponseListener<Document>() {
    @Override
    public void onSuccess(Document object) {
      // callback called once the create action has been completed
      // => the result is a Document object
    }

    @Override
    public void onError(JSONObject error) {
      // Handle error
    }
  });

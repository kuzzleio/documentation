
dataMapping.apply(new ResponseListener<CollectionMapping>() {
   @Override
   public void onSuccess(CollectionMapping object) {
     // called once the mapping action has been completed
   }

   @Override
   public void onError(JSONObject error) {
     // Handle error
   }
});

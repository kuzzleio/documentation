
kuzzle.security.getMyRights(new ResponseListener<JSONObject[]>() {
    @Override
    public void onSuccess(JSONObject[] rights) {
        // Policies is an enum with the following properties:
        // allowed, denied, conditional
        Policies authorization = kuzzle.security.isActionAllowed(rights, "read", "get", "index1", "collection1");
    }

    @Override
    public void onError(JSONObject error) {
     // ...
    }
});

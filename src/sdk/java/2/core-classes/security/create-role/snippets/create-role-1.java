
JSONObject roleDefinition = new JSONObject()
  .put("controllers", new JSONObject()
    .put("*", new JSONObject()
      .put("actions", new JSONObject()
        .put("*", true)
      )
    )
  )
);

Options opts = new Options().setReplaceIfExist(true);

kuzzle
  .security
  .createRole("myrole", roleDefinition, opts, new ResponseListener<Role>() {
    @Override
    public void onSuccess(Role role) {
      // the result is an instantiated Role object
    }

    @Override
    public void onError(JSONObject error) {

    }
  })

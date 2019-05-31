
JSONObject roleDefinition = new JSONObject()
  .put("controllers", new JSONObject()
    .put("*", new JSONObject()
      .put("actions", new JSONObject()
        .put("*", true)
      )
    )
  )
);

kuzzle
  .security
  .updateRole("Role ID", roleDefinition, new ResponseListener<Role>() {
    @Override
    public void onSuccess(Role role) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });

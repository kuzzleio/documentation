
JSONObject roleDefinition = new JSONObject()
  .put("controllers", new JSONObject()
    .put("document", new JSONObject()
      .put("actions", new JSONObject()
        .put("get", true)
      )
    )
  )
);

role.update(roleDefinition, new ResponseListener<Role>() {
  @Override
  public void onSuccess(Role updatedRole) {

  }

  @Override
  public void onError(JSONObject error) {

  }
});

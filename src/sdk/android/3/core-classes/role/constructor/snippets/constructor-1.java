
JSONObject roleDefinition = new JSONObject()
  .put("controllers", new JSONObject()
    .put("*", new JSONObject()
      .put("actions", new JSONObject()
        .put("*", true)
      )
    )
  )
);

Role role = new Role(kuzzle.security, "role ID", roleDefinition);

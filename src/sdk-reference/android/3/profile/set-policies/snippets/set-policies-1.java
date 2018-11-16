
// Binding role IDs to a profile
profile.setPolicies(new String[]{"role1 ID", "role2 ID", "role3 ID"});

// Binding policies definition to a profile
profile.setPolicies(new JSONObject[]{
  new JSONObject().put('roleId', 'role1 ID'),
  new JSONObject().put('roleId', 'role2 ID'),
  new JSONObject().put('roleId', 'role3 ID')
});

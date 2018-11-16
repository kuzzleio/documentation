
JSONObject
  strategyCredentials = new JSONObject().put("some", "credentials"),
  credentials = new JSONObject().put("<strategy name>", strategyCredentials);

user.setCredentials(credentials);

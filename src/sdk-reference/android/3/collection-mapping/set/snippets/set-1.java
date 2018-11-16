
JSONObject mapping = new JSONObject();
mapping.put("type", "string");
mapping.put("index", "analyzed");
mapping.put("null_value", "");

dataMapping.set("field", mapping);

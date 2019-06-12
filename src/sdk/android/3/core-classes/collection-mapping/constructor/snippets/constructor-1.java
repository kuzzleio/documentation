
CollectionMapping dataMapping = new CollectionMapping(dataCollection);

JSONObject mapping = new JSONObject();
JSONObject type = new JSONObject();
type.put("type", "string");
mapping.put("foo", type);

CollectionMapping dataMapping = new CollectionMapping(dataCollection, mapping);

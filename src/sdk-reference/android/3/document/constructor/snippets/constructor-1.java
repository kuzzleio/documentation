
Document document = new Document(collection);

Document document = new Document(collection, "id");

JSONObject content = new JSONObject();
content.put("content", "some content");
Document document = new Document(collection, content);

Document document = new Document(collection, "id", content);

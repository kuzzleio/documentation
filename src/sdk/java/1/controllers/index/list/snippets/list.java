StringVector indexes = kuzzle.getIndex().list();

for (int i = 0; i < indexes.size(); ++i) {
  System.out.println(indexes.get(i));
}

System.out.println(String.format("Kuzzle contains %d indexes", indexes.size()));

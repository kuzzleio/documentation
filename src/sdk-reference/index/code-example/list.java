StringVector indexes = kuzzle.getIndex().list();

System.out.println(String.format("Kuzzle contains %d indexes", indexes.size()));

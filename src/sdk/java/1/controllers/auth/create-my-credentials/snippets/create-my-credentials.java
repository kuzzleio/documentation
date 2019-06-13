kuzzle.getAuth().login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
kuzzle.getAuth().createMyCredentials("other",  "{\"username\":\"foo\",\"password\":\"bar\"}");
System.out.println("Success");
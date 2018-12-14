#include "kuzzle.hpp"
#include "websocket.hpp"
#include <iostream>

using kuzzleio::Kuzzle;

#define K_INDEX_NAME "nyc-open-data"
#define K_COLLECTION_NAME "yellow-taxi"


int main ( int argc, char * argv[])
{
	std::string res;

	Kuzzle *k = new Kuzzle(new kuzzleio::WebSocket("localhost"));
	try {
		k->connect();
	} 
	catch(KuzzleException &e) {
		std::cerr << "Failled to connect to Kuzzle Server" << std::endl;
		std::cerr << e.what() << std::endl;
		exit(1);
	}

  std::cout << "Connected to Kuzzle Server" << std::endl;

	std::string document = R"(
		{
			"name": "Sirkis",
			"birthday": "1959-06-22",
			"license": "B"
		}
	)";

	try {
		res = k->document->create(K_INDEX_NAME, K_COLLECTION_NAME, "", document);
	}
	catch( KuzzleException &e) {
		std::cerr << "Error, Failed to created document" << std::endl;
		k->disconnect();
		exit(1);
	}

	std::cout << "Document created successfuly" << std::endl;
	std::cout << res << std::endl;

	k->disconnect();
	
	return 0;
}
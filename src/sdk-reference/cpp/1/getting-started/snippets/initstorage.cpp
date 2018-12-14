#include "kuzzle.hpp"
#include "websocket.hpp"
#include <iostream>

using kuzzleio::Kuzzle;

#define K_INDEX_NAME "nyc-open-data"
#define K_COLLECTION_NAME "yellow-taxi"


int main ( int argc, char * argv[])
{
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

	try {
		k->index->create(K_INDEX_NAME);
	}
	catch (kuzzleio::KuzzleException &e) {
		std::cerr << "Warn: failled to create index: " << K_INDEX_NAME << std::endl;
		std::cerr << e.what() << std::endl;
	}

	try {
		k->collection->create(K_INDEX_NAME, K_COLLECTION_NAME);
	}
	catch (kuzzleio::KuzzleException &e) {
		std::cerr << "Error: failled to create collection: " << K_COLLECTION_NAME << std::endl;
		std::cerr << e.what() << std::endl;
		exit(1);
	}

	std::cout << "Index '" << K_INDEX_NAME << "' and collection '" << K_COLLECTION_NAME << "' created" << std::endl; 
	k->disconnect();
	
	return 0;
}


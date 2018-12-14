#include "kuzzle.hpp"
#include "websocket.hpp"
#include <iostream>

using kuzzleio::Kuzzle;

int main ( int argc, char * argv[])
{
    Kuzzle *k = new Kuzzle(new kuzzleio::WebSocket("localhost"));
    k->connect();
    std::cout << "Server now = " << k->server->now() << std::endl;
    k->disconnect();
    
    return 0;
}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->sdiffstore('key', ['key1', 'key2', '...'], 'destination');
}
catch (ErrorException $e) {

}

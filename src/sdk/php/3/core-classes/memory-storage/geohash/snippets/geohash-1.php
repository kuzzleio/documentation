
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $hashes = $kuzzle->memoryStorage()->geohash('key', ['Palermo', 'Catania']);
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $positions = $kuzzle->memoryStorage()->geopos('key', ['Palermo', 'Catania']);
}
catch (ErrorException $e) {

}

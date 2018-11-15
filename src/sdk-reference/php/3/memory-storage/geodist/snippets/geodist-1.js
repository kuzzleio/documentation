
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $distance = $kuzzle->memoryStorage()->geodist('key', 'Palermo', 'Catania');
}
catch (ErrorException $e) {

}

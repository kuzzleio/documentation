
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $property = $kuzzle->memoryStorage()->object('key', 'encoding');
}
catch (ErrorException $e) {

}

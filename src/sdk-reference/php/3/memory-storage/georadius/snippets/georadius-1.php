
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $points = $kuzzle->memoryStorage()->georadius('key', 15, 37, 200, 'km');
}
catch (ErrorException $e) {

}

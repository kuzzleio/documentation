
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $ping = $kuzzle->memoryStorage()->ping();
}
catch (ErrorException $e) {

}

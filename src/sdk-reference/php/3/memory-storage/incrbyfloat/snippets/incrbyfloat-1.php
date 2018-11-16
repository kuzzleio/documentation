
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->incrbyfloat('key', -3.14159);
}
catch (ErrorException $e) {

}

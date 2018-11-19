
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->decrby('key', 42);
}
catch (ErrorException $e) {

}

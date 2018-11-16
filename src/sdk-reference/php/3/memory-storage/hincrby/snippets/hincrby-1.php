
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->hincrby('key', 'field', 42);
}
catch (ErrorException $e) {

}

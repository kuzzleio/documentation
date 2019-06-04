
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->memoryStorage()->setex('key', 'value', 42);
}
catch (ErrorException $e) {

}

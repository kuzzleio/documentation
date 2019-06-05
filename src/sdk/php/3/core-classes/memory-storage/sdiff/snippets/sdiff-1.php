
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $diff = $kuzzle->memoryStorage()->sdiff('key', ['key1', 'key2', '...']);
}
catch (ErrorException $e) {

}

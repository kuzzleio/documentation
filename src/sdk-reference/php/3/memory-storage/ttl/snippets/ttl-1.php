
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $ttl = $kuzzle->memoryStorage()->ttl('key');
}
catch (ErrorException $e) {

}

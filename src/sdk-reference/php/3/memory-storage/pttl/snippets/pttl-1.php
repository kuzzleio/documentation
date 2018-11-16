
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $ttl = $kuzzle->memoryStorage()->pttl('key');
}
catch (ErrorException $e) {

}

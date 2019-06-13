
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->srem('key', ['member1', 'member2', '...']);
}
catch (ErrorException $e) {

}

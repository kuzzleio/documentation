
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->zunionstore('destination', ['key1', 'key2', '...']);
}
catch (ErrorException $e) {

}

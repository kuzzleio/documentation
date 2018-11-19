
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->zinterstore('destination', ['key1', 'key2', '...']);
}
catch (ErrorException $e) {

}

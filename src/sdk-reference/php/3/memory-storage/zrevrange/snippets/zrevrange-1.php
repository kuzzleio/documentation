
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $members = $kuzzle->memoryStorage()->zrevrange('key', 0, -1);
}
catch (ErrorException $e) {

}

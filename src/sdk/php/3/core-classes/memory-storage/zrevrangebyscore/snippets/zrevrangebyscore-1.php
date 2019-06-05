
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $members = $kuzzle->memoryStorage()->zrevrangebyscore('key', 2, 3);
}
catch (ErrorException $e) {

}

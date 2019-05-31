
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->zremrangebyscore('key', 1, 2);
}
catch (ErrorException $e) {

}

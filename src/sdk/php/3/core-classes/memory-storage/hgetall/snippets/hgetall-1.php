
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $hash = $kuzzle->memoryStorage()->hgetall('key');
}
catch (ErrorException $e) {

}

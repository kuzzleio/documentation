
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $values = $kuzzle->memoryStorage()->lrange('key', 0, 1);
}
catch (ErrorException $e) {

}

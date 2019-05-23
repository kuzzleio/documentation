
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->getrange('key', 2, 4);
}
catch (ErrorException $e) {

}

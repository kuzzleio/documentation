
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $bit = $kuzzle->memoryStorage()->getbit('key', 10);
}
catch (ErrorException $e) {

}

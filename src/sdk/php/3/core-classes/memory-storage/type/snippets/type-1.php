
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $type = $kuzzle->memoryStorage()->type('key');
}
catch (ErrorException $e) {

}

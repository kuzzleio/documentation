
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $length = $kuzzle->memoryStorage()->hstrlen('key', 'field');
}
catch (ErrorException $e) {

}

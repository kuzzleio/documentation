
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $status = $kuzzle->memoryStorage()->setnx('key', 'value');
}
catch (ErrorException $e) {

}

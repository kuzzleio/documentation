
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->memoryStorage()->rename('key', 'newId');
}
catch (ErrorException $e) {

}

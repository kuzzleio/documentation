
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->memoryStorage()->psetex('key', 'value', 42000);
}
catch (ErrorException $e) {

}

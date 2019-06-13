
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $status = $kuzzle->memoryStorage()->hsetnx('key', 'field', 'value');
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $status = $kuzzle->memoryStorage()->renamenx('key', 'newId');
}
catch (ErrorException $e) {

}

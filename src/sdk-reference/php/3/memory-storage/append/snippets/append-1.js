
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $newLength = $kuzzle->memoryStorage()->append('key', 'value');
}
catch (ErrorException $e) {

}

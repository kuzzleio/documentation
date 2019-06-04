
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $oldValue = $kuzzle->memoryStorage()->getset('key', 'oldValue');
}
catch (ErrorException $e) {

}

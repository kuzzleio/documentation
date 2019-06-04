
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->lrem('key', 1, 'foo');
}
catch (ErrorException $e) {

}

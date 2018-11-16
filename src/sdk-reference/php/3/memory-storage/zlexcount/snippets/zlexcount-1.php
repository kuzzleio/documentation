
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->zlexcount('key', '[b', '[f');
}
catch (ErrorException $e) {

}

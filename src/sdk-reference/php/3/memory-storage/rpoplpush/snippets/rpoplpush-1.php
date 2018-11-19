
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->rpoplpush('sourceKey', 'destKey');
}
catch (ErrorException $e) {

}

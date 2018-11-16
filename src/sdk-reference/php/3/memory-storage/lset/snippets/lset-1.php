
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->memoryStorage()->lset('key', 2, 'bar');
}
catch (ErrorException $e) {

}

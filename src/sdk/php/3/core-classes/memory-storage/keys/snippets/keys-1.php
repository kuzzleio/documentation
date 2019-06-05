
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $keys = $kuzzle->memoryStorage()->keys('foo*');
}
catch (ErrorException $e) {

}

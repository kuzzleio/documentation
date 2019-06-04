
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $page = $kuzzle->memoryStorage()->sscan('key', 0);
}
catch (ErrorException $e) {

}

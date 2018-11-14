
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $page = $kuzzle->memoryStorage()->zscan('key', 0);
}
catch (ErrorException $e) {

}

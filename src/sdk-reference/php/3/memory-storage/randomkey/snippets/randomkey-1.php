
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $key = $kuzzle->memoryStorage()->randomkey();
}
catch (ErrorException $e) {

}

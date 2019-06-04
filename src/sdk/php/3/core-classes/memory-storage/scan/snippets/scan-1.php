
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $page = $kuzzle->memoryStorage()->scan(0);
}
catch (ErrorException $e) {

}

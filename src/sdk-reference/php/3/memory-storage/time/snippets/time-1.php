
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $result = $kuzzle->memoryStorage()->time();
}
catch (ErrorException $e) {

}

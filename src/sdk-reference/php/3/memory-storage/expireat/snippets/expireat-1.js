
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $status = $kuzzle->memoryStorage()->expireat('key', 1488372354);
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->zcount('key', 2, 3);
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->zremrangebyrank('key', 1, 2);
}
catch (ErrorException $e) {

}

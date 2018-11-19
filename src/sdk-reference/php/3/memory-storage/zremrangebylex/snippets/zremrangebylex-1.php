
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->zremrangebylex('key', '[b', '(f');
}
catch (ErrorException $e) {

}

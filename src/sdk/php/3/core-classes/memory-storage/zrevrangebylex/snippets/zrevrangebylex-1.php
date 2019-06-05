
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $members = $kuzzle->memoryStorage()->zrevrangebylex('key', '-', '(g');
}
catch (ErrorException $e) {

}

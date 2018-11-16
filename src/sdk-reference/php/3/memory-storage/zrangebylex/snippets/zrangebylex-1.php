
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $members = $kuzzle->memoryStorage()->zrangebylex('key', '-', '(g');
}
catch (ErrorException $e) {

}

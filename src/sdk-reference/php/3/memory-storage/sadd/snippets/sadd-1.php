
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->sadd('key', ['foo', 'bar', 'baz']);
}
catch (ErrorException $e) {

}

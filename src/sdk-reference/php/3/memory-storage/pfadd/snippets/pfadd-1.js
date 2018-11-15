
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $status = $kuzzle->memoryStorage()->pfadd('key', ['foo', 'bar', 'baz']);
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->memoryStorage()->pfmerge('key', ['key1', 'key2', '...']);
}
catch (ErrorException $e) {

}

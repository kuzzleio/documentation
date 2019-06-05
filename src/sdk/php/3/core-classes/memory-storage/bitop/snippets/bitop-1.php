
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $length = $kuzzle->memoryStorage()->bitop('key', 'AND', ['srckey1', 'srckey2', '...']);
}
catch (ErrorException $e) {

}

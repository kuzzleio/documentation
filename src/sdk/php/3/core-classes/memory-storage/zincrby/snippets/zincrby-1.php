
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->zincrby('key', 'foo', 3.14159);
}
catch (ErrorException $e) {

}

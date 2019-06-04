
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->memoryStorage()->ltrim('key', 1, 2);
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->lindex('key', 3);
}
catch (ErrorException $e) {

}

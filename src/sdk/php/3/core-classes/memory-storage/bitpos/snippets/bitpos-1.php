
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $position = $kuzzle->memoryStorage()->bitpos('key', 0);
}
catch (ErrorException $e) {

}

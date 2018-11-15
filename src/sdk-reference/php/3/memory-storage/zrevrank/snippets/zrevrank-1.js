
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $position = $kuzzle->memoryStorage()->zrevrank('key', 'foo');
}
catch (ErrorException $e) {

}

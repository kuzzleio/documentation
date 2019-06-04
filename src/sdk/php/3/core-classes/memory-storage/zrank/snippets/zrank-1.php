
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $position = $kuzzle->memoryStorage()->zrank('key', 'foo');
}
catch (ErrorException $e) {

}

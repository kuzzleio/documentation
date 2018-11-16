
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->lpushx('key', 'foo');
}
catch (ErrorException $e) {

}

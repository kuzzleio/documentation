
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->rpushx('key', 'foo');
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->hget('key', 'field1');
}
catch (ErrorException $e) {

}

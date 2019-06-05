
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->hdel('key', ['field1', 'field2']);
}
catch (ErrorException $e) {

}

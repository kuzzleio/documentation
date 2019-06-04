
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $values = $kuzzle->memoryStorage()->hmget('key', ['field1', 'field2', '...']);
}
catch (ErrorException $e) {

}

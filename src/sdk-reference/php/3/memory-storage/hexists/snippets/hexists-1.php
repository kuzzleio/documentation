
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $exists = $kuzzle->memoryStorage()->hexists('key', 'field1');
}
catch (ErrorException $e) {

}

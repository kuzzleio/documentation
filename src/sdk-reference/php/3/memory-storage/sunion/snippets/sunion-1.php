
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $values = $kuzzle->memoryStorage()->sunion(['key1', 'key2', '...']);
}
catch (ErrorException $e) {

}

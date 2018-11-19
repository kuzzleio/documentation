
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $values = $kuzzle->memoryStorage()->sinter(['key1', 'key2', '...']);
}
catch (ErrorException $e) {

}

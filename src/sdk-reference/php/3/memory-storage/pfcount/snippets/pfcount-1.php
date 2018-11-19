
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->pfcount(['key1', 'key2', '...']);
}
catch (ErrorException $e) {

}

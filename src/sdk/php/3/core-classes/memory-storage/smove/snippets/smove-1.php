
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $status = $kuzzle->memoryStorage()->smove('key', 'destination', 'member');
}
catch (ErrorException $e) {

}

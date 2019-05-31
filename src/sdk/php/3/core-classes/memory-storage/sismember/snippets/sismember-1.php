
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $isMember = $kuzzle->memoryStorage()->sismember('key', 'member');
}
catch (ErrorException $e) {

}

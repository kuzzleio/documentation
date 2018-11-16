
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $fields = $kuzzle->memoryStorage()->hkeys('key');
}
catch (ErrorException $e) {

}

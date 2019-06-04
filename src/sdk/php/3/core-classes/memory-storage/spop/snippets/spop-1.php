
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $elements = $kuzzle->memoryStorage()->spop('key');
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $score = $kuzzle->memoryStorage()->zscore('key', 'bar');
}
catch (ErrorException $e) {

}

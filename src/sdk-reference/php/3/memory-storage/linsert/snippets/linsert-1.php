
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $count = $kuzzle->memoryStorage()->linsert('key', 'after', 'foo', 'bar');
}
catch (ErrorException $e) {

}

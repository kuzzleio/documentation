
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $value = $kuzzle->memoryStorage()->hincrbyfloat('key', 'field', 3.14159);
}
catch (ErrorException $e) {

}

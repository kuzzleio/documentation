
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

try {
  $points = $kuzzle->memoryStorage()->georadiusbymember('key', 'Palermo', 200, 'km');
}
catch (ErrorException $e) {

}


<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->logout();
  // everything went fine
}
catch (ErrorException $e) {

}

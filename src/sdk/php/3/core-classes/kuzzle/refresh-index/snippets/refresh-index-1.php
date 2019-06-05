
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->refreshIndex('myIndex');
}
catch (ErrorException $e) {

}

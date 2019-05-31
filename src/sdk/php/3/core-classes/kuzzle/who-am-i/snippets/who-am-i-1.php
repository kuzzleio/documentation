
<?php
use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;

$kuzzle = new Kuzzle('localhost');

try {
  $me = $kuzzle->whoAmI();
  // $me instanceof User
}
catch (ErrorException $e) {

}

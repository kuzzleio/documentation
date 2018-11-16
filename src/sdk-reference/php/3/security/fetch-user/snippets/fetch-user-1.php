
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;

$kuid = 'myUser';

$kuzzle = new Kuzzle('localhost');

try {
  $user = $kuzzle->security()->fetchUser($kuid);

  // $user instanceof User
}
catch (ErrorException $e) {

}

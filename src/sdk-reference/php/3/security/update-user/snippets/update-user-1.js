
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;

$kuid = 'myUser';
$userDefinition = [
  'firstname' => 'My Name Is',
  'lastname' => 'Jonas'
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

try {
  $user = $security->updateUser($kuid, $userDefinition);

  // $user instanceof User
}
catch (ErrorException $e) {

}

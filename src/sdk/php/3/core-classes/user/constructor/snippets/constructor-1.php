
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;

$kuid = 'myUser';
$userDefinition = [
  // A "profileIds" field is required to bind a user to an existing profile
  'profileIds' => ['myProfile'],
  // The "local" authentication strategy requires a password
  'password' => 'secret',
  'firstname' => 'John',
  'lastname' => 'Doe'
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

// Using the Security factory
$user = $security->user($kuid, $userDefinition);

// Or directly with the constructor:
$user = new User($security, $kuid, $userDefinition);


<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;

$kuid = 'myUser';
$userDefinition = [
    'content' => [
      // A "profileIds" field is required to bind a user to existing profiles
      'profileIds' => ['admin'],
      // You can also set custom fields to your user
      'firstname' => 'John',
      'lastname' => 'Doe'
    ],
    'credentials' => [
      // Authentication strategy to use
      'local' => [
        // The necessary information to provide vary,
        // depending on the chosen authentication strategy
        'password' => 'secret password',
        'username' => 'jdoe'
      ]
    ]
  ];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

try {
  $user = $security->createUser($kuid, $userDefinition);

  // $user instanceof User
}
catch (ErrorException $e) {

}

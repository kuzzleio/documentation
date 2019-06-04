
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;

$kuid = 'myUser';
$userDefinition = [
    'content' => [
    ],
    'credentials' => [
      'local' => [
        // The "local" authentication strategy requires a password
        'password' => 'secretPassword',
        'lastLoggedIn' => 1494411803
      ]
    ]
  ];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

try {
  $user = $security->createRestrictedUser($kuid, $userDefinition);

  // $user instanceof User
}
catch (ErrorException $e) {

}

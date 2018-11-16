
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Role;

$roleId = 'myRole';
$roleDefinition = [
  'controllers' => [
    '*' => [
      'actions' => [
        '*' => true
      ]
    ]
  ]
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

// Using the Security factory:
$role = $security->role($roleId, $roleDefinition);

// Or directly with the constructor:
$role = new Role($security, $roleId, $roleDefinition);

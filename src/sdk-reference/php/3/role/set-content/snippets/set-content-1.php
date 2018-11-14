
<?php

use Kuzzle\Security\Role;

// ...

$roleDefinition = [
  'controllers' => [
    '*' => [
      'actions' => [
        '*' => true
      ]
    ]
  ]
];

/*
 * @var $role Role
 */
$role->setContent($roleDefinition);

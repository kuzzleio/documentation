
<?php

use Kuzzle\Security\Role;

// ...

$roleDefinition = [
  'controllers' => [
    'document' => [
      'actions' => [
        'get' => true
      ]
    ]
  ]
];

/*
 * @var $role Role
 */

try {
  $role = $role->update($roleDefinition);

  // $role instanceof Role
}
catch (ErrorException $e) {
  // error occured
}

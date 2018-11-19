
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Role;
use \Kuzzle\Util\RolesSearchResult;

// optional: retrieve only roles allowing access to the
// provided controller names
$filters = [
  'controllers' => [
    'document',
    'security'
  ]
];

// optional: result pagination configuration
$options = [
  'size' => 10,
  'from' => 0
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

try {
  $result = $security->searchRoles($filters, $options);

  // $result instanceof RolesSearchResult

  foreach($result->getRoles() as $role) {
    // $role instanceof Role
  }
}
catch (ErrorException $e) {

}

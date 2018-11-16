
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Role;

$roleId = 'myRole';

$kuzzle = new Kuzzle('localhost');

try {
  $role = $kuzzle->security()->fetchRole($roleId);

  // $role instanceof Role
}
catch (ErrorException $e) {

}

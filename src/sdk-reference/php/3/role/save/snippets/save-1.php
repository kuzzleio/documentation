
<?php

use Kuzzle\Security\Role;

// ...

/*
 * @var $role Role
 */

try {
  $role = $role->save();

  // $role instanceof Role
}
catch (ErrorException $e) {
  // error occured
}

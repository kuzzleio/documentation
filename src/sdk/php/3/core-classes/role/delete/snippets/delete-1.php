
<?php

use Kuzzle\Security\Role;

// ...

/*
 * @var $role Role
 */
try {
  $role->delete();
}
catch(ErrorException $e) {
  // error occurred
}

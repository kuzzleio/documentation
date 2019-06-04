
<?php

use Kuzzle\Security\User;

// ...

/*
 * @var $user User
 */

try {
  $user = $user->save();

  // $user instanceof User
}
catch (ErrorException $e) {
  // error occured
}


<?php

use Kuzzle\Security\User;

// ...

/*
 * @var $user User
 */

try {
  $user->delete();
}
catch (ErrorException $e) {

}

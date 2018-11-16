
<?php

use Kuzzle\Security\User;

// ...

/*
 * @var $user User
 */

try {
  $user->saveRestricted();
}
catch (ErrorException $e) {

}

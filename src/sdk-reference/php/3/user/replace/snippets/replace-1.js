
<?php

use Kuzzle\Security\User;

// ...

/*
 * @var $user User
 */

try {
  $user->replace();
}
catch (ErrorException $e) {

}


<?php

use Kuzzle\Security\User;

// ...

/*
 * @var $user User
 */

try {
  $user->create();
}
catch (ErrorException $e) {

}

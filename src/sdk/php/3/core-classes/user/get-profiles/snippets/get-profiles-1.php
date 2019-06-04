
<?php

use Kuzzle\Security\User;

// ...

/*
 * @var $user User
 */

try {
  for ($user->getProfiles() as $profile) {
    // $profile is a Profile object
  }
}
catch (ErrorException $e) {

}

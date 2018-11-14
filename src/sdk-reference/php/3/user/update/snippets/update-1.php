
<?php

use Kuzzle\Security\User;

// ...

/*
 * @var $user User
 */

$userContent = [
  'firstname' => 'My Name Is',
  'lastname' => 'Jonas'
];

try {
  $user->update($userContent);
}
catch (ErrorException $e) {

}

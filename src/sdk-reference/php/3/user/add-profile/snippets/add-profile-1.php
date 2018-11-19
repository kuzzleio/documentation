
<?php

use Kuzzle\Security\Profile;
use Kuzzle\Security\User;

// ...

$profile = $kuzzle->security->fetchProfile('myProfile');

/*
 * @var $user User
 */

// Updating the profile with a Profile object
$user->addProfile($profile);

// Updating the profile with a profile ID
$user->addProfile('myProfile');

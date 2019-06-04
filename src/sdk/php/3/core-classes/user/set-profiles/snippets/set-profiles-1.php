
<?php

use Kuzzle\Security\Profile;
use Kuzzle\Security\User;

// ...

$profile = $kuzzle->security->fetchProfile('myProfile');

/*
 * @var $user User
 */

// Updating the profile with a Profile object
$user->setProfiles([$profile]);

// Updating the profile with a profile ID
$user->setProfiles(['myProfile']);

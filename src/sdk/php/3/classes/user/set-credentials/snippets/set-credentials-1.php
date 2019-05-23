
<?php

use Kuzzle\Security\User;


/*
 * @var $user User
 */

// Updating the profile with a Profile object
$user->setCredentials([
    '<strategy name>' => [
        'some' => 'credentials'
    ]
]);

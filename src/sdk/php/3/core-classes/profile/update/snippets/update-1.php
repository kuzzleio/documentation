
<?php

use Kuzzle\Security\Profile;

// ...

/*
 * @var $profile Profile
 */
$profileContent = [
  'policies' => [
    ['roleId' => 'myrole']
  ]
];

try {
  $profile->update($profileContent);
}
catch (ErrorException $e) {

}

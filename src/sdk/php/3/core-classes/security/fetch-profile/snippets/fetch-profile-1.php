
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Profile;

$profileId = 'myProfile';

$kuzzle = new Kuzzle('localhost');

try {
  $profile = $kuzzle->security()->fetchProfile($profileId);

  // $profile instanceof Profile
}
catch (ErrorException $e) {

}

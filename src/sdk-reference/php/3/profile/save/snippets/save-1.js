
<?php

use Kuzzle\Security\Profile;

// ...

/*
 * @var $profile Profile
 */

try {
  $profile = $profile->save();

  // $profile instanceof Profile
}
catch (ErrorException $e) {

}

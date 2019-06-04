
<?php

use Kuzzle\Security\Profile;

// ...

/*
 * @var $profile Profile
 */
try {
  $profile->delete();
}
catch(ErrorException $e) {

}

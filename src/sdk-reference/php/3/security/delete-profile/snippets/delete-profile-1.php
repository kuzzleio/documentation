
<?php

use \Kuzzle\Kuzzle;

$profileId = 'myProfile';

$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->security()->deleteProfile($profileId);
}
catch (ErrorException $e) {

}

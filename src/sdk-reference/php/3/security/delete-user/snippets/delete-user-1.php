
<?php

use \Kuzzle\Kuzzle;

$kuid = 'myUser';

$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->security()->deleteUser($kuid);
}
catch (ErrorException $e) {

}

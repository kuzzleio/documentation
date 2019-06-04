
<?php

use \Kuzzle\Kuzzle;

$roleId = 'myRole';

$kuzzle = new Kuzzle('localhost');

try {
  $kuzzle->security()->deleteRole($roleId);
}
catch (ErrorException $e) {

}

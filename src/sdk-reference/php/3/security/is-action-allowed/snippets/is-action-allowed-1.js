
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Security;

$kuzzle = new Kuzzle('localhost');

try {
  $rights = $kuzzle->security()->getMyRights();

  switch ($kuzzle->security()->isActionAllowed($rights, 'read', 'get', 'index1', 'collection1')) {
    case Security::ACTION_ALLOWED:
      // code...
      break;
    case Security::ACTION_DENIED:
      // code...
      break;
    case Security::ACTION_CONDITIONAL:
      // code...
      break;
  }
}
catch (ErrorException $e) {

}

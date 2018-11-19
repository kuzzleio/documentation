
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\Security;

$kuzzle = new Kuzzle('localhost');

// using the static instance
$security = $kuzzle->security();

// or instantiating a new Security object
$security = new Security($kuzzle);

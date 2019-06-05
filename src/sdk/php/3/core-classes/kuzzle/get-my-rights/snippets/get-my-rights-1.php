
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$rights = $kuzzle->security()->getMyRights();

// $rights is an array of associative arrays

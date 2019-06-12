
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

// Removes all listeners on the "queryError" global event
$kuzzle->removeAllListeners('queryError');

// Removes all listeners on all global events
$kuzzle->removeAllListeners();

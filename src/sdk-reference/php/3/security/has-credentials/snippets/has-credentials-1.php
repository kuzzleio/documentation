
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$result = $kuzzle->security->hasCredentials('local', 'kuid');

// $result is a boolean

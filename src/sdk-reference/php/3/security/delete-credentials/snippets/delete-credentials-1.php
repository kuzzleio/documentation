
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$result = $kuzzle->security->deleteCredentials('local', 'kuid');


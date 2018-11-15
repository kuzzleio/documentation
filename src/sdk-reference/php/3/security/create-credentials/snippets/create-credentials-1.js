
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$credentials = $kuzzle->security->createCredentials('local', 'kuid', ['username' => 'foo']);


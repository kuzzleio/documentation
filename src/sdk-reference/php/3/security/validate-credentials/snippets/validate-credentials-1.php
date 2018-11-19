
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$result = $kuzzle->security->validateCredentials('local', 'kuid', ['username' => 'foo']);

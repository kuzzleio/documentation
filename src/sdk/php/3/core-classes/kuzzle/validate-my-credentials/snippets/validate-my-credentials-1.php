
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$result = $kuzzle->validateMyCredentials('local', ['username' => 'foo']);

// $result = true or false

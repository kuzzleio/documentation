
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$result = $kuzzle->createMyCredentials('local', ['username' => 'foo']);

// $result = [username => 'foo', kuid => '<kuid>']

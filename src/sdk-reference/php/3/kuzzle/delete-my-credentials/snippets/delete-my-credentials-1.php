
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$result = $kuzzle->deleteMyCredentials('local');

// $result = [acknowledged => true]

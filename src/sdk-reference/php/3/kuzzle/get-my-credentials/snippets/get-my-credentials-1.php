
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$credentials = $kuzzle->getMyCredentials('local');

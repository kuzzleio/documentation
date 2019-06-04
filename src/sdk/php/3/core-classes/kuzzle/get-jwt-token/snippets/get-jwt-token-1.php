
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
// ...
$token = $kuzzle->getJwtToken();

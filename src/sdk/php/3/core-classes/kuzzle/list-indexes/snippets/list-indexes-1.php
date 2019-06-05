
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$indexes = $kuzzle->listIndexes();

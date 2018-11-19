
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

$kuzzleTime = $kuzzle->now();

// $kuzzleTime instanceof DateTime

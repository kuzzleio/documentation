
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
// Date can be either in ISO format or a timestamp (utc, in milliseconds)
$date = time() * 1000;
$statistics = $kuzzle->getStatistics($date);

// $statistics is an array of statistics objects

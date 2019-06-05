
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$statistics = $kuzzle->getStatistics();

// $statistics is an array of statistics

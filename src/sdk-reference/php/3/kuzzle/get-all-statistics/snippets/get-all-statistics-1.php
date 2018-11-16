
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$stats = $kuzzle->getAllStatistics();

foreach($stats as $frame) {
  // loop through all returned frames
}

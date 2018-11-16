
<?php

use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

$points = [
  [
    'lon' => 13.361389,
    'lat' => 38.115556,
    'name' => 'Palermo'
  ],
  [
    'lon' => 15.087269,
    'lat' => 37.502669,
    'name' => 'Catania'
  ]
];

try {
  $count = $kuzzle->memoryStorage()->geoadd('key', points);
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;

$filters = [];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $count = $dataCollection->count($filters);
}
catch (ErrorException $e) {
}

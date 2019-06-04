
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$collections = $kuzzle->listCollections('index', [
  'type' => 'stored',
  'from' => 0,
  'size' => 42
]);


<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

// or using a default index:
$kuzzle = new Kuzzle('localhost', [
  'defaultIndex' => 'some index'
]);
$dataCollection = $kuzzle->collection('collection', 'index');

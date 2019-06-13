
<?php

use \Kuzzle\Kuzzle;

$headers = [
  'someContent' => 'someValue'
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');
$dataCollection->setHeaders($headers, true);

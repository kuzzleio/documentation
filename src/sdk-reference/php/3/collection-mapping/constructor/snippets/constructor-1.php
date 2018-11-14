
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\DataMapping;

$mapping = [
  'someField' => [
    'type' => 'string',
    'index' => 'analyzed'
  ]
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

$dataMapping = $dataCollection->collectionMapping($mapping);
// $dataMapping instanceof DataMapping

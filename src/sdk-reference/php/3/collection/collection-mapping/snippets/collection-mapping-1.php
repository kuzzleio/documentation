
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

try {
  $dataMapping = $dataCollection->collectionMapping($mapping);

  // $dataMapping instanceof DataMapping
  $dataMapping->apply();
}
catch (ErrorException $e) {

}

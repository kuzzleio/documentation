
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Document;

$documentId = 'foobar';
$documentContent = [
  'title' => 'foo',
  'content' => 'bar'
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

// You can use the factory embeded in DataCollection object
$document = $dataCollection->document($documentId, $documentContent);

// or directly with public constructor
$document = new Document($dataCollection, $documentId, $documentContent);

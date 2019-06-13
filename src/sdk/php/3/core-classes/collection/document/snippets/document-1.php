
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
$document = $dataCollection->document($documentId, $documentContent);

// $document instanceof Document

try {
  $document->save();
}
catch (ErrorException $e) {

}

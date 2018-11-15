
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Document;

$documentId = 'foobar';
$documentContent = [
  'newField' => 'foo'
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $document = $dataCollection->updateDocument($documentId, $documentContent);

  // $document instanceof Document
}
catch (ErrorException $e) {

}

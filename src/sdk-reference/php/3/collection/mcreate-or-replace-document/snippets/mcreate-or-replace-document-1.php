
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Document;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

$firstDocument = new Document($dataCollection, 'doc1', ['title' => 'foo', 'content' => 'bar']);
$secondDocument = new Document($dataCollection, 'doc2', ['title' => 'foo', 'content' => 'bar']);

try {
  $result = $dataCollection->mCreateOrReplaceDocument([$firstDocument, $secondDocument]);
}
catch (ErrorException $e) {

}

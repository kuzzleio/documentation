
<?php

use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

// Deleting one document
try {
  $result = $dataCollection->deleteDocument('documentId');
}
catch (ErrorException $e) {

}

// Deleting multiple documents
$filters = [
  'filter' => [
    'equals' => ['field' => 'value']
  ]
];

try {
  $result = $dataCollection->deleteDocument($filters);
}
catch (ErrorException $e) {

}

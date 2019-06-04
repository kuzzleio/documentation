
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Document;
use \Kuzzle\Util\SearchResult;

function processDocument ($doc) {
  // do something with the document
}

$kuzzle = new Kuzzle('localhost');
$collection = $kuzzle->collection('collection', 'index');

try {
  $searchResult = $collection->search([
    'from' => 0,
    'size' => 1000,
    'scroll' => '30s',
    'filter' => []
  ]);
  
  while ($searchResult !== null) {
    foreach ($searchResult->getDocuments() as $doc) {
      procesDocument($doc);
    }
    
    $searchResult = $searchResult->fetchNext();
  }
}
catch (ErrorException $e) {
  // Handle errors here
}

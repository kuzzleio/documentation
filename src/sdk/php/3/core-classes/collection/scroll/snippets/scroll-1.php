
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Document;
use \Kuzzle\Util\SearchResult;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $searchResult = $dataCollection->scroll($scrollId, ['scroll' => '1m']);

  // $searchResult instanceof SearchResult
  $searchResult->getTotal();

  foreach($searchResult->getDocuments() as $document) {
    // $document instanceof Document
  }

  // return an array representing the aggregations response
  $searchResult->getAggregations();
}
catch (ErrorException $e) {

}

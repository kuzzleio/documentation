
<?php

use \Kuzzle\Kuzzle;

$filters = [
    "match_all" => [
        "boost" => 1
    ]
];

$options = [
  'from' => 0,
  'size' => 20
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $res = $dataCollection->searchSpecifications($filters, $options);
  
  foreach ($res['hits'] as $specification) {
    // Specification
  }
  
  // Total specifications count
  $res['total'];
}
catch (ErrorException $e) {

}

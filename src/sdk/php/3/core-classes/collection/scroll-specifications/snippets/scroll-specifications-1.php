
<?php

use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $res = $dataCollection->scrollSpecifications($scrollId, ['scroll' => '1m']);

  foreach ($res['hits'] as $specification) {
    // Specification
  }
  
  // Total specifications count
  $res['total'];
}
catch (ErrorException $e) {

}

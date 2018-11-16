
<?php

use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $result = $dataCollection->mDeleteDocument(['doc1', 'doc2']);
}
catch (ErrorException $e) {

}

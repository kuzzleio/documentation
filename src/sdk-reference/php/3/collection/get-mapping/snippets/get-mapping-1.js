
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\DataMapping;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $mapping = $dataCollection->getMapping();

  // $mapping instanceof DataMapping
}
catch (ErrorException $e) {

}

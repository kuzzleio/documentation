
<?php

use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $specifications = $dataCollection->getSpecifications();
}
catch (ErrorException $e) {

}

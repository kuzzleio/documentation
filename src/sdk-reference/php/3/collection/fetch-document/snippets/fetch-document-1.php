
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Document;

$documentId = 'foobar';

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $document = $dataCollection->fetchDocument($documentId);

  // $document instanceof Document
}
catch (ErrorException $e) {

}


<?php

use \Kuzzle\Kuzzle;

$message = [
  'field' => 'value'
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $result = $dataCollection->publishMessage($message);
}
catch (ErrorException $e) {

}

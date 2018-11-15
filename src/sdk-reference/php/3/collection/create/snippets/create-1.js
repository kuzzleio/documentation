
<?php

use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

// Optional: a mapping can be provided and will be
// applied when the collection is created
$mapping = [
  'properties' => [
    'field1' => [
      'type' => '<es field type>'
    ],
    'field2' => [
      'type' => '<es field type>'
    ]
  ]
];

try {
  $dataCollection->create(mapping);
}
catch (ErrorException $e) {

}

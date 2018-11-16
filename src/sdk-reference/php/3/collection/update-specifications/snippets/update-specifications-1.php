
<?php

use \Kuzzle\Kuzzle;

$specifications = [
    'strict' => true,
    'fields' => [
        'foo' => [
            'mandatory' => true,
            'type' => 'string',
            'defaultValue' => 'bar'
        ]
    ]
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  // result is an array
  $res = $dataCollection->updateSpecifications($specifications);
}
catch (ErrorException $e) {

}


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
  // $isValid is a boolean
  $isValid = $dataCollection->validateSpecifications($specifications);
}
catch (ErrorException $e) {

}

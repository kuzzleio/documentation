
<?php

use \Kuzzle\DataMapping;

// ...

$field = 'field';
$mapping = [
  'type' => 'string',
  'index' => 'analyzed',
  'null_value' => ''
];

/**
 * @var $dataMapping DataMapping
 */
$dataMapping->set($field, $mapping);

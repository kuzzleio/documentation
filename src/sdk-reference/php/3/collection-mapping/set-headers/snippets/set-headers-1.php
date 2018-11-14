
<?php

use \Kuzzle\DataMapping;

// ...

$headers = [
  'someContent' => 'someValue'
];

/**
 * @var $dataMapping DataMapping
 */
$dataMapping->setHeaders($headers);

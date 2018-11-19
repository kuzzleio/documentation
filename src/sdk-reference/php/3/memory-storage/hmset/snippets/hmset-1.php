
<?php

use \Kuzzle\Kuzzle;


$kuzzle = new Kuzzle('localhost');

$entries = [
  ['field' => 'field1', 'value' => 'foo'],
  ['field' => 'field2', 'value' => 'bar'],
  ['field' => '...', 'value' => '...']
];

try {
  $kuzzle->memoryStorage()->hmset('key', entries);
}
catch (ErrorException $e) {

}

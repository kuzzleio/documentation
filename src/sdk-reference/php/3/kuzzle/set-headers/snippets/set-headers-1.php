
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');

$newHeaders = [
  'someContent' => 'someValue'
];

$kuzzle->setHeaders($newHeaders);

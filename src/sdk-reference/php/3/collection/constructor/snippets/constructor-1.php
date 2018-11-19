
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Collection;

$kuzzle = new Kuzzle('localhost');
$dataCollection = new Collection($kuzzle, 'my-collection', 'my-index');

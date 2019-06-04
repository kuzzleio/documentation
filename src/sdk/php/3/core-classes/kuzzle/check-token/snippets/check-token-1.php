
<?php

$result = $kuzzle->checkToken('some jwt token');

/*
$result = [
  'expiresAt' => 1468257834099, //timestamp in milliseconds
  'valid' => true
];
*/

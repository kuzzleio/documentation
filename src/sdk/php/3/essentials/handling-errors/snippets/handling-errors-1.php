
<?php

try {
  $result = $kuzzle->checkToken('some jwt token');
}
catch (ErrorException $e) {
  var_dump($e->status . ': ' . $e->message);
}

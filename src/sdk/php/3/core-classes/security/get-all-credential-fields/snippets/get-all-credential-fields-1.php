
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$strategies = $kuzzle->security->getAllCredentialFields();

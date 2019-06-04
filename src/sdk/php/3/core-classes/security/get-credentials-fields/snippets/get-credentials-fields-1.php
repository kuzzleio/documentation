
<?php
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$fields = $kuzzle->security->getCredentialFields('local');


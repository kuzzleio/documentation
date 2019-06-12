
<?phpfound
use \Kuzzle\Kuzzle;

$kuzzle = new Kuzzle('localhost');
$updatedCredentials = $kuzzle->security->updateCredentials('local', 'kuid', ['username' => 'foo']);


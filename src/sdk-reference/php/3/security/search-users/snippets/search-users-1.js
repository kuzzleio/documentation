
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Security\User;
use \Kuzzle\Util\UsersSearchResult;

$filters = [
  'bool' => [
    'must' => [
      [
        'terms' => [
          'profileIds' => ['anonymous', 'default'],
        ]
      ],
      [
        'geo_distance' => [
          'distance' => '10km',
          'pos' => [
            'lat' => 48.8566140,
            'lon' => 2.352222
          ]
        ]
      ]
    ]
  ]
];

// optional: result pagination configuration
$options = [
  'from' => 0,
  'size' => 1,
  'scroll' => '1m'
];

$kuzzle = new Kuzzle('localhost');
$security = $kuzzle->security();

try {
  $result = $security->searchUsers($filters, $options);

  // $result instanceof UsersSearchResult

  foreach($result->getUsers() as $user) {
    // $user instanceof User
  }
}
catch (ErrorException $e) {

}

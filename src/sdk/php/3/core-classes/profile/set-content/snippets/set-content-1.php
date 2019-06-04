
<?php

use Kuzzle\Security\Profile;

// ...

$profileDefinition = [
  'policies' => [
    [
      'roleId' => 'anonymous',
      'restrictedTo' => [
        ['index' => 'my-second-index', 'collection' => ['my-collection']]
      ]
    ]
  ]
];

/*
 * @var $profile Role
 */
$profile->setContent($profileDefinition);

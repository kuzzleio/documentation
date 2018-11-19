
<?php

use \Kuzzle\Kuzzle;
use \Kuzzle\Document;
use \Kuzzle\Util\SearchResult;

$body = [
  'query' => [
    'bool' => [
      'must' => [
        [
          'terms' => ['status' => ['idle', 'wantToHire', 'toHire', 'riding']]
        ],
        ['term' => 'cab'],
        [
          'geo_distance' => [
            'distance' => '10km',
            'pos' => [
              'lat' => '48.8566140',
              'lon' => '2.352222'
            ]
          ]
        ]
      ]
    ]
  ],
  'sort' => [
    'status',
    [
      '_geo_distance' => [
        'pos' => ['lat' => '48.8566140', 'lon' => '2.352222'],
        'order' => 'asc'
      ]
    ],
    ['date' => 'desc']
  ],
  'aggregations' => [
    'aggs_name' => [
      'terms' => [
        'field' => 'field_name'
      ]
    ]
  ]
];

$options = [
  'from' => 0,
  'size' => 20
];

$kuzzle = new Kuzzle('localhost');
$dataCollection = $kuzzle->collection('collection', 'index');

try {
  $searchResult = $dataCollection->search($body, $options);

  // $searchResult instanceof SearchResult
  $searchResult->getTotal();

  foreach($searchResult->getDocuments() as $document) {
    // $document instanceof Document
  }

  // return an array representing the aggregations response
  $searchResult->getAggregations();
}
catch (ErrorException $e) {

}

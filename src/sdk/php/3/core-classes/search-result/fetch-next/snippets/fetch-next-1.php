
<?php

use \Kuzzle\SearchResult;

// ...

/**
 * @var $searchResult SearchResult
 */

try {
  $nextSearchResult = $searchResult->fetchNext();
} catch (ErrorException $e) {
    // Handle error
}

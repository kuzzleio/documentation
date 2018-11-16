
<?php

use \Kuzzle\Document;

// ...

/**
 * @var $document Document
 */

try {
  $document = $document->refresh();
} catch (ErrorException $e) {

}

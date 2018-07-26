Connects to Kuzzle using the `host` property provided in the constructor options.  
Subsequent call have no effect if the SDK is already connected.  

## Signature

```javascript
/**
 * @returns {Promise}
 */
connect()
```

## Resolve

Resolve without value if connection is made successfully.

## Reject

Reject with a [Kuzzle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[code-example=connect]

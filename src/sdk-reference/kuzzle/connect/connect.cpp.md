Connects to Kuzzle using the `host` argument provided in the constructor options.  
Subsequent call have no effect if the SDK is already connected.  

## Signature

```cpp
char* connect()
```

## Return

A `char*` with the error message or `nullptr` if connection is made successfully.

## Usage

[code-example=connect]

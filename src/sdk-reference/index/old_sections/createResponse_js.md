Returns an object with the index creation status.

| Name | Type | Description
|------|------|-------------
| acknowledged | boolean | indicates whether the index was successfully created in the Elastic cluster
| shards_acknowledged | boolean | indicates whether the requisite number of shard copies were started for each shard in the index before timing out

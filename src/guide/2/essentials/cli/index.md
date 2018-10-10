---
layout: full.html.hbs
algolia: true
title: Command Line Interface (CLI)
Order: 900
---

# Command Line Interface (CLI)

Kuzzle ships with a [Command line interface](https://en.wikipedia.org/wiki/Command-line_interface) which allows you to:

* Start Kuzzle
* Gracefully shutdown Kuzzle
* Create the first Administrator
* Reset Kuzzle internal data _(use with caution!)_
* Reset user created indexes _(use with caution!)_
* Reset users, roles and profiles _(use with caution!)_
* Clear Kuzzle cache
* Diagnose the Kuzzle installation

The CLI is located in the `bin` folder of your Kuzzle installation.  
If you have already created an admin, you will need to provide your login information to the CLI.  
To get a list of commands and options run the CLI:

```bash
./bin/kuzzle

#   Usage: kuzzle [options] [command]
#
#
#   Commands:
#
#     createFirstAdmin           create the first administrator user
#     clearCache                 clear internal caches in Redis
#     reset                      reset all users, profiles, roles and documents validation specifications
#     resetSecurity              reset all users, profiles and roles
#     resetDatabase              remove all data stored on Kuzzle
#     shutdown                   gracefully exits after processing remaining requests
#     start [options]            start a Kuzzle instance
#     dump                       create a dump of current state of kuzzle
#
#   Options:
#
#     -h, --help                 output usage information
#     -V, --version              output the version number
#     -p, --port <port>          Kuzzle port number
#     -h, --host <host>          Kuzzle host
#     -U, --username <username>  Admin username
#     -P, --password <password>  Admin password
#     -d, --debug                make errors more verbose
#     -C, --noColors             do not use ANSI coloring
```

---

## createFirstAdmin

```bash
./bin/kuzzle createFirstAdmin
```

When Kuzzle runs for the first time, no users are defined and the anonymous user is granted full access rights.

The `createFirstAdmin` command lets you create an administrator to manage security.

<div class="alert alert-info">NB: This command can only be run interactively</div>

This call the action [security#createFirstAdmin]({{ site_base_path }}api-documentation/controller-security/create-first-admin)

---

## clearCache

```bash
./bin/kuzzle clearCache
```

Kuzzle uses Redis to store frequently accessed internal data. Use this command if you need to clear this data (cache).

This call the action [admin#resetCache]({{ site_base_path }}api-documentation/controller-admin/reset-cache)

---

## dump

```bash
./bin/kuzzle dump

# [ℹ] Creating dump file...
# [✔] Done!
#
# [ℹ] Dump has been successfully generated in "dump/<date>-<time>-cli" folder
# [ℹ] You can send the folder to the kuzzle core team at support@kuzzle.io
```

The `dump` command creates a snapshot of the state of Kuzzle, including:

* a coredump of Kuzzle
* the current Kuzzle configuration
* server logs
* Node.js binary & properties
* a list of OS properties
* plugins configuration
* usage statistics of the dumped instance

The generated directory can be used to feed a crash report to the support team.

This call the action [admin#dump]({{ site_base_path }}api-documentation/controller-admin/reset-security)

---

## reset

```bash
./bin/kuzzle reset --help

#    Usage: reset [options]
#
#    reset all users, profiles, roles and documents validation specifications
#
#    Options:
#
#      -h, --help             output usage information
#      --noint                non interactive mode
```

Asynchronously start the following sequence in Kuzzle, in this order:
* Invalidate and delete all users along with their credentials
* Delete all user-defined roles and profiles
* Reset the default roles and profiles to their default values
* Delete all document validation specifications

This action has no impact on Plugin and Document storage.

This call the action [admin#resetKuzzleData]({{ site_base_path }}api-documentation/controller-admin/reset-kuzzle-data)

---

## resetSecurity

{{{since "1.4.0"}}}

```bash
./bin/kuzzle resetSecurity --help

#    Usage: resetSecurity [options]
#
#    reset all users, profiles and roles
#
#    Options:
#
#      -h, --help             output usage information
#      --noint                non interactive mode
```

The `resetSecurity` command deletes all created users, profiles and roles and reset the default roles and profiles : `anonymous`, `admin` and `default`.

This call the action [admin#resetSecurity]({{ site_base_path }}api-documentation/controller-admin/reset-security)

---

## resetDatabase

{{{since "1.4.0"}}}

```bash
./bin/kuzzle resetDatabase --help

#    Usage: resetDatabase [options]
#
#    delete all data stored on Kuzzle
#
#    Options:
#
#      -h, --help             output usage information
#      --noint                non interactive mode
```

The `resetDatabase` delete all indexes created by users. This does not include Kuzzle's internal index.

This call the action [admin#resetDatabase]({{ site_base_path }}api-documentation/controller-admin/reset-database)  

Note: this command has no impact on any plugins stored data, or on any Kuzzle stored documents.

---

## shutdown

```bash
./bin/kuzzle shutdown

# [ℹ] Shutting down...
# [✔] Done!
```

The `shutdown` command lets you stop a Kuzzle instance after any remaining requests are processed, ensuring that no unnecessary `Service Unavailable` errors are returned to connected clients.

This call the action [admin#shutdown]({{ site_base_path }}api-documentation/controller-admin/shutdown)

---

## start

```bash
./bin/kuzzle start --help

#    Usage: start [options]
#
#    start a Kuzzle instance
#
#    Options:
#
#      -h, --help                 output usage information
#          --fixtures <file>      import data from file
#          --mappings <file>      apply mappings from file
#          --securities <file>    import roles, profiles and users from file

```

The `start` command starts a Kuzzle instance.

Using this command you can also initialize the storage layer mappings, using the mappings `--mappings` options, and the storage layer documents using the `--fixtures` option.

#### `--mappings`

Loads mappings from a file and apply them to the storage layer.

The input file must be a JSON file with the following structure:

```json
{
  "index": {
    "collection": {
      "properties": {
        "field1": {},
        "field2": {},
        "field...": {}
      }
    },
  }
}
```

**Notes:**

* The file can contain any number of index and collection configurations.
* Field definitions follow the [Elasticsearch](https://www.elastic.co/guide/2/en/elasticsearch/reference/5.5/mapping.html) mapping format.
* If an index or collection does not exist, it will be created automatically.
* Mappings are loaded sequentially, one index/collection pair at a time. If a failure occurs, Kuzzle immediately interrupts the sequence.
* Mappings can be replayed across multiple Kuzzle start sequences, as long as they do not change in-between.


**Example:**

```json
{
  "foo": {
    "bar": {
      "properties": {
        "foobar": {"type": "keyword"},
        "barfoo": {"type": "integer"}
      }
    },
    "baz": {
      "properties": {
        "created": {"type": "date", "format": "strict_date_optional_time||epoch_millis"},
        "other": {"type": "text"}
      }
    }
  }
}
```

#### `--fixtures`

Reads documents from a file and loads them into the storage layer.

The file must be a JSON file with the following structure:

```json
{
  "index": {
    "collection": [
      {"<command>": {}},
      {"field": "value", "field2": "value", "field...", "value"}
    ]
  }
}
```

**Notes:**

* The file can contain any number of index and collection configurations.
* Each collection contains an array of data to load, just like the [bulk:import API]({{ site_base_path }}api-documentation/controller-bulk/import/).
* If an index or collection does not exist, the load will fail.
* Fixtures are loaded sequentially, one index/collection pair at a time. If a failure occurs, Kuzzle immediately interrupts the sequence.


**Example:**

```json
{
  "foo": {
    "bar": [
      {"index": {}},
      {"field": "foo", "another_field": 42},
      {"index": {}},
      {"field": "foo", "another_field": 42}
    ],
    "baz": [
      {"index": {}},
      {"bar": "baz", "qux": ["q", "u", "x"]}
    ]
  }
}
```

#### `--securities`

Read roles, profiles and users from a file and loads them into the storage layer.

The file must be a JSON file with the following structure:

```json
{
  "roles": {
    "role-id": {
      /* role definition */
    }
  },
  "profiles": {
    "profile-id": {
      /* profile definition */
    }
  },
  "users": {
    "user-id": {
      /* user definition */
    }
  }
}
```

The roles, profiles and users definition follow the same structure as in the body parameter of the API:
 - [createRole](https://docs.kuzzle.io/api-documentation/controller-security/create-role)
 - [createProfile](https://docs.kuzzle.io/api-documentation/controller-security/create-profile)
 - [createUser](https://docs.kuzzle.io/api-documentation/controller-security/create-user)

**Notes:**

* The file can contain any number of roles, profiles and users.
* If a role, profile or user already exists, it will be replaced.
* Fixtures are loaded sequentially, first the roles, then the profiles and finally the users. If a failure occurs, Kuzzle immediately interrupts the sequence.

**Example:**

```json
{
	"roles": {
		"role-id-1": {
			"controllers": {
				"*": {
					"actions": {
						"*": true
					}
				}
			},
			"role-id-2": {
				"controllers": {
					"*": {
						"actions": {
							"*": true
						}
					}
				}
			}
		},
		"profiles": {
			"profile-id-1": {
				"policies": [{
					"roleId": "role-id-1",
					"restrictedTo": []
				}]
			},
			"profile-id-2": {
				"policies": [{
					"roleId": "role-id-2",
					"restrictedTo": []
				}]
			}
		},
		"users": {
			"user-id-1": {
				"content": {
					"profileIds": ["profile-id-1"]
				},
				"credentials": {
					"local": {
						"username": "nina",
						"password": "thug"
					}
				}
			},
			"user-id-2": {
				"content": {
					"profileIds": ["profile-id-2"]
				},
				"credentials": {
					"local": {
						"username": "alyx",
						"password": "vance"
					}
				}
			}
		}
	}
}
```

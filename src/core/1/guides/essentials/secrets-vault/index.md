---
code: false
type: page
title: Secrets Vault
order: 700
---

# Secrets Vault

<SinceBadge version="1.8.0" />

When you develop an application with Kuzzle, you may need to use secrets such as API keys or authentication information.  

Of course, it is out of the question to version these secrets in clear with the rest of your source code.  

However, it is still practical to be able to share these secrets with the rest of your team, or to add them to the repository for automated production. 

Kuzzle offers a secure storage system for these secrets, the operation is as follows:
  - writing secrets to a JSON file,
  - manual encryption of this file with a password,
  - adding the encrypted file to the repository,
  - automatic decryption of the file when Kuzzle starts,
  - exposure of secrets in the context of plugins.

Thus, the only secret that it is necessary to communicate to the rest of his team is the encryption password of this file.

## Secrets file format

The secrets file is in JSON format. Each string value will be encrypted but the key names will remain the same.

```js
/* config/secrets.json */
{
  "aws": {
    "secretKeyId": "lfiduras"
  },
  "cloudinaryKey": "ho-chi-minh"
}
```

Once encrypted, the file will be the following:

```js
/* config/secrets.enc.json */
{
  "aws": {
    "secretKeyId": "536553f3181ada6f700cac98100f1266.3181ada66536553f"
  },
  "cloudinaryKey": "f700cac98100f1266536553f3181ada6.6536553f3181ada"
}
```

## Encrypt and decrypt with the CLI

The encryption of a secret file is done by means of the CLI with the following command:

```bash
./bin/kuzzle encryptSecrets config/secrets.json --vault-key strongpassword
[ℹ] Encrypting secrets...
[✔] Secrets successfully encrypted: config/secrets.enc.json
```

The file `config/secreys.enc.json` can be added safely to the project repository.

To decrypt a previously encrypted file, use the following command:

```bash
./bin/kuzzle decryptSecrets config/secrets.enc.json --vault-key strongpassword
[ℹ] Decrypting secrets...
[✔] Secrets successfully encrypted: config/secrets.json
```

## Load encrypted secrets at startup

Kuzzle will try to decrypt the provided file in one of the following ways in order of priority:
  - in the command `./bin/kuzzle start --secrets-file /var/secrets.enc.json`
  - in an environment variable `export KUZZLE_SECRETS_FILE=/var/secrets.enc.json`
  - the default one present at the location `<kuzzle dir>/config/secrets.enc.json`

The decryption key must be provided in one of the following ways, always in order of priority:
  - in the command `./bin/kuzzle start --vault-key verystrongpassword`
  - in an environment variable `export KUZZLE_VAULT_KEY=verystrongpassword`

Kuzzle start is interrupted if:
  - a decryption key is provided and Kuzzle cannot find a file
  - Kuzzle finds a file and no decryption key is provided

## Access secrets in plugin

Once Kuzzle has successfully loaded the file containing the secrets, he will expose its content to all the plugins.  

Secrets are accessible in the property [context.secrets](/core/1/plugins/plugin-context/secrets).  

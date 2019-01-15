const credentials = { username: 'foo', password: 'bar' };

try {
  await kuzzle.auth.login('local', credentials);

  const user = await kuzzle.auth.getCurrentUser();

  const profiles = await user.getProfiles();

  const roles = await profiles[0].getRoles();
  console.log(roles);
  /*
    [
      Role {
        _id: 'default',
        controllers: {
          '*': {
            actions: {
              '*': true
            }
          }
        }
      }
    ]
  */
  console.log(`Successfully retrieved ${roles.length} roles.`);
} catch (error) {
  console.error(error.message);
}

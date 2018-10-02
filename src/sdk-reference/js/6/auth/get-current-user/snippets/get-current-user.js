try {
  await kuzzle.auth.getCurrentUser();
  console.log('Success');
} catch (error) {
  console.error(error.message);
}

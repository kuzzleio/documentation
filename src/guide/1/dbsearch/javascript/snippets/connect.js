const run = async () => {
  try {
    // Wait for etablished connection to Kuzzle
    await kuzzle.connect();
  } catch (error) {
    console.error(error.message);
  } finally {
    // Disconnecting kuzzle
    kuzzle.disconnect();
  }
};
    
run();
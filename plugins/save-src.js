module.exports = options => {
  return (files, metalsmith, done) => {
    setImmediate(done);

    for (const file in files) {
      if (files.hasOwnProperty(file)) {
        files[file].src = file;
      }
    }
  };
};

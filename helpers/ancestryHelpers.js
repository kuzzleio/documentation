const ancestryHelpers = {

  getLastChildren : (file) => {
    if (file.ancestry.firstChild) {
      return ancestryHelpers.getLastChildren(file.ancestry.firstChild);
    }
    return file;
  }

};

module.exports = ancestryHelpers;
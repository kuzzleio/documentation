const cheerio = require('cheerio');

module.exports = {
  process(filename, data) {
    const anchors = [],
      ids = new Set(),
      $ = cheerio.load(data.contents.toString());

    $('h1, h2').each((i, el) => {
      const id = `${$(el)
        .text()
        .replace(/&.*?/g, '')
        .replace(/\s+/g, '-')
        .replace(/[^\w-]/g, '')
        .replace(/[-]+/g, '-')
        .toLowerCase()}`;

      if (!ids.has(id)) {
        anchors.push({
          text: $(el).html(),
          id
        });
        ids.add(id);
      }

      $(el).attr('id', id);
    });

    return {
      anchors,
      fileContent: Buffer.from($.html())
    };
  }
};

const cheerio = require('cheerio');

module.exports = {
  process(filename, data) {
    const
      anchors = [],
      ids = new Set(),
      $ = cheerio.load(data.contents.toString());

    $('h1, h2').each((i, el) => {
      const
        section = $(el).parent('.section'),
        language = (section.length > 0) ? section.attr('class').split(' ')[1] : 'default',
        id = `${$(el).text().replace(/&.*?/g, '').replace(/\s+/g, '-').replace(/[^\w-]/g, '').replace(/[-]+/g, '-').toLowerCase()}-${language}`;

      if (! ids.has(id)) {
        anchors.push({
          text: $(el).html(),
          id,
          language
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

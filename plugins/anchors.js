const cheerio = require('cheerio');

module.exports = {
  process(filename, data) {
    let anchors = [];
    let $ = cheerio.load(data.contents.toString());
    $('h2').each((i, el) => {
      let 
        section = $(el).parent('.section'),
        language = (section.length > 0) ? section.attr('class').split(' ')[1] : 'default',
        id = $(el).text().replace(/&.*?/g, '').replace(/\s+/g, '-').replace(/[^\w\-]/g, '').replace(/[\-]+/g, '-').toLowerCase();

      anchors.push({
        'text': $(el).text(),
        'id': `${id}-${language}`,
        'language': language
      });
      $(el).attr('id', `${id}-${language}`);
    });
    return {
      anchors: anchors,
      fileContent: new Buffer($.html())
    };
  }
}
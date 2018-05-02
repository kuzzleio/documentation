const cheerio = require('cheerio');

module.exports = {
  process(filename, data) {
    let anchors = [];
    let $ = cheerio.load(data.contents.toString());
    $('h2').each((i, el) => {
      let id = $(el).text().replace(/&.*?/g, '').replace(/\s+/g, '-').replace(/[^\w\-]/g, '').replace(/[\-]+/g, '-').toLowerCase() 
      anchors.push({
        'text': $(el).text(),
        'id': id
      })
      $(el).attr('id', id)
    });
    return {
      anchors: anchors,
      fileContent: new Buffer($.html())
    }
  }
}
const cheerio = require('cheerio');

module.exports = {
  process(filename, data) {
    let anchors = [];
    let $ = cheerio.load(data.contents.toString());
    $('h2').each((i, el) => {
      const id = $(el).text().toLowerCase().replace(new RegExp(' ', 'g'), '-')
      anchors.push({
        'text': $(el).text(),
        'id': $(el).text().toLowerCase().replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '').replace(new RegExp(' ', 'g'), '-')
      })
    });
    return anchors;
  }
}
const cheerio = require('cheerio');

const algoliaHelpers = {

  fileParser : (file, data) => {
    const 
      objects = [],
      $ = cheerio.load(data.contents.toString(), {
        normalizeWhitespace: true 
      }),
      content = $('.md-content');
    
    // remove useless content
    $('pre', content).remove();
    $('h1, h2, h3, h4, h5, h6', content).remove();
  
    objects.push({
      objectID: data.path,
      title: data.title,
      description: data.description ? data.description : '',
      path: data.path,
      tags: algoliaHelpers.extractTags(data.path),
      content: content.text(),
      parent: (data.ancestry.parent ? data.ancestry.parent.title : ''),
      firstMember: (data.ancestry.firstMember ? data.ancestry.firstMember.title : ''),
      toc: data.toc
    });
    
    return objects;
  },
  
  extractTags : (path) => {
    const 
      start = 0,
      end = 4;
    
    return path.split('/').slice(start, end).map(tag => {
      if (tag === 'sdk-reference') { return 'sdk'; }
      if (/^\+?(0|[1-9]\d*)$/.test(tag)) { return `${tag}.x`; }
      return tag;
    });
  }

};

module.exports = algoliaHelpers;
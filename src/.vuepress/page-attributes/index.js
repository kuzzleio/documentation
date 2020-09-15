const values = require('lodash/values')
module.exports = (opts, ctx) => ({
  name: 'PageAttributes',

  extendPageData($page) {
    const { sections } = opts;
    const {
      _filePath, // file's absolute path
      _computed, // access the client global computed mixins at build time, e.g _computed.$localePath.
      _content, // file's raw content string
      _strippedContent, // file's content string without frontmatter
      key, // page's unique hash key
      frontmatter, // page's frontmatter object
      regularPath, // current page's default link (follow the file hierarchy)
      path: pagePath // current page's real link (use regularPath when permalink does not exist)
    } = $page;

    $page.fullPath = `${_computed.$site.base}${pagePath}`.replace('//', '/')
    const sectionPath = Object.keys(sections).find(path => $page.fullPath.startsWith(path))
    $page.currentSection = sections[sectionPath]
  }
})
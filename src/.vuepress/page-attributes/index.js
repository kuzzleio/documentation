const transform = require('lodash/transform');

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
    $page.sectionsByPath = sections
    $page.sectionList = transform(
      sections,
      (result, value, key) => {
        result.push({ ...value, path: key });
      },
      []
    );
    $page.currentSection = $page.sectionList.find(s => $page.fullPath.startsWith(s.path))
  }
})
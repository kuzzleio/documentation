export const VERSION_QUERY_KEY = 'version';
export const DEFAULT_VERSION = 2

export const getCurrentVersion = (page, route) => {
  if (!page.currentSection) {
    if (!route.query[VERSION_QUERY_KEY]) {
      return DEFAULT_VERSION;
    } else {
      return parseInt(route.query[VERSION_QUERY_KEY]);
    }
  }

  return page.currentSection.kuzzleMajor;
}
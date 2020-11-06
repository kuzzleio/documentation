export const VERSION_QUERY_KEY = 'version';
export const DEFAULT_VERSION = 2

export const getCurrentVersion = (page, state) => {
  if (!page.currentSection) {
    if (!state) {
      return DEFAULT_VERSION;
    } else {
      return state;
    }
  }

  return page.currentSection.kuzzleMajor;
}
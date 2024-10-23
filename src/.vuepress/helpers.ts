import { PageData } from "vuepress";

import { ExtraPageData } from "./page-attributes";

export const VERSION_QUERY_KEY = 'version';
export const DEFAULT_VERSION = 2;

export const getCurrentVersion = (page: PageData<ExtraPageData>) => {
  if (!page.currentSection) {
    return DEFAULT_VERSION;
  }

  return page.currentSection.kuzzleMajor;
};

export const createMetaTag = (property: string, content: string) => {
  const meta = document.createElement('meta');
  meta.setAttribute('property', property);
  meta.setAttribute('content', content);

  return meta;
};

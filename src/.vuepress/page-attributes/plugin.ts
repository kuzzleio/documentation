import { transform } from 'lodash-es';
import { Page, PluginFunction } from 'vuepress';

import {
  ExtraPageData,
  PageAttributesPluginOptions,
  PageAttributesSection,
  PageAttributesSectionWithPath,
 } from './types';

export const PLUGIN_NAME = '@kuzzleio/vuepress-plugin-page-attributes';

export const pageAttributesPlugin =
  ({ sections }: PageAttributesPluginOptions): PluginFunction =>
  (app) => {
    return {
      name: PLUGIN_NAME,
      extendsPage: (page: Page<Partial<ExtraPageData>>) => {
        const fullPath = `${app.siteData.base}${page.path}`.replace('//', '/');
        page.data.fullPath = fullPath;

        /**
         * Headers used to be part of `page.data`, but since VuePress rc.26
         * they only live on `page`, which stays on the server. The TOC needs
         * them on the client, so carry them over.
         */
        page.data.headers = page.headers;

        page.data.sectionsByPath = sections;
        page.data.sectionList = transform<PageAttributesSection, PageAttributesSectionWithPath[]>(
          sections,
          (result, value, key) => {
            result.push({ ...value, path: key });
          },
          []
        );
        page.data.currentSection = page.data.sectionList.find(s => fullPath.startsWith(s.path))
      },
    };
  }

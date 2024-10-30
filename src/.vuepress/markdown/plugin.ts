import { Logger } from '@vuepress/helper';
import { PluginFunction } from 'vuepress';

import codeSnippet from './code-snippet';

export const PLUGIN_NAME = '@kuzzleio/vuepress-plugin-markdown';
const logger = new Logger(PLUGIN_NAME);

export const markdownPlugin =
  (): PluginFunction =>
  (app) => {
    return {
      name: PLUGIN_NAME,
      extendsMarkdown: (md) => {
        md.use(codeSnippet, {
          docsDir: app.dir.source(),
          logger,
        });
      },
    };
  }

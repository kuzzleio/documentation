import { path } from '@vuepress/utils';
import { assign, endsWith, mapValues } from 'lodash-es';
import { App, Page } from "vuepress";

import { LintError, MissingKeyLintError } from "./types";

const codeRE = /api-reference\/.+|protocols\/context\/|protocols\/entrypoint\/|protocols\/methods\/|plugins\/events\/.+|plugin-context|controllers\/.+|core-classes\/.+|virtual-classes\/.+|sdk\/.+\/protocols\/.+|core-structs\/.+|sdk\/.+\/classes\/.+/;

function computeMissingKeyFix(url: string, error: MissingKeyLintError, nodes: Page[]) {
  const node = getNodeByPath(url, nodes);

  if (!node) {
    return;
  }

  if (error.key === 'code') {
    if (codeRE.test(node.path)) {
      return {
        code: true
      };
    } else {
      return {
        code: false
      };
    }
  }

  if (error.key === 'type') {
    const children = getChildren(node, nodes);
    if (children.length) {
      if (
        node.frontmatter.type !== 'branch' &&
        node.frontmatter.type !== 'root'
      ) {
        return { type: 'branch' };
      }
    } else {
      if (node.frontmatter.type !== 'page') {
        return { type: 'page' };
      }
    }
  }
}

function getChildren(node: Page, nodes: Page[]) {
  const pathRE = new RegExp(`^${getNodeDirectory(node)}[a-zA-z_0-9\-]+/?$`);
  return nodes.filter(p => p.path.match(pathRE) && p.path !== node.path);
}

function getNodeDirectory(node: Page) {
  if (endsWith(node.path, '/')) {
    return node.path;
  } else {
    return path.parse(node.path).dir;
  }
}

function getNodeByPath(path: string, nodes: Page[]) {
  return nodes.find(p => p.path === path);
}

export function appendFixes(errorsByPath: Record<string, LintError[]>, ctx: App) {
  return mapValues(errorsByPath, (errors, url) => {
    return errors.map(e => {
      if (e.error !== 'MISSING_KEY') {
        return e;
      }

      const fix = computeMissingKeyFix(url, e, ctx.pages);
      if (fix) {
        return assign({ fix }, e);
      } else {
        return e;
      }
    });
  });
}

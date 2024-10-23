import { get, endsWith } from 'lodash-es';
import path from 'path';

export function setItemLocalStorage(key: string, item: string | object) {
  const storeItem = typeof item === 'object' ? JSON.stringify(item) : item;
  localStorage.setItem(key, storeItem);
}

function getPageDir(page) {
  if (endsWith(page.path, '/')) {
    return page.path;
  } else {
    return path.dirname(page.path);
  }
}

function getParentPath(node) {
  return path.normalize(`${getPageDir(node)}../`);
}

function sortPagesByOrderAndTitle(p1, p2) {
  const o1 = +get(p1, 'meta.frontmatter.order', NaN);
  const o2 = +get(p2, 'meta.frontmatter.order', NaN);

  if (isNaN(o1) && !isNaN(o2)) {
    return 1;
  }
  if (isNaN(o2) && !isNaN(o1)) {
    return -1;
  }
  if (isNaN(o1) || o1 === o2) {
    return p1.meta.frontmatter.title < p2.meta.frontmatter.title ? -1 : 1;
  }

  return o1 < o2 ? -1 : 1;
}

function groupHeaders(headers) {
  // group h3s under h2
  headers = headers.map(h => Object.assign({}, h));
  let lastH2;
  headers.forEach(h => {
    if (h.level === 2) {
      lastH2 = h;
    } else if (lastH2) {
      (lastH2.children || (lastH2.children = [])).push(h);
    }
  });
  return headers.filter(h => h.level === 2);
}

export const getPageChildren = (page, pages, fullDetpth = false) => {
  const pathRE = new RegExp(`^${getPageDir(page)}[a-zA-z_0-9\-]+/?${fullDetpth ? '' : '$'}`);

  return pages
    .filter(p => p.path.match(pathRE) && p.path !== page.path)
    .sort(sortPagesByOrderAndTitle);
};

export const findRootNode = (node, nodes) => {
  if (node.frontmatter.type === 'root' || node.path === '/') {
    return node;
  }
  const parent = getParentNode(node, nodes);
  if (!parent) {
    return node;
  }

  return findRootNode(parent, nodes);
};

export const getFirstValidChild = (node, nodes) => {
  const children = getPageChildren(node, nodes, true);

  if (!children.length) {
    return node;
  }

  return getFirstValidChild(children[0], nodes);
};

export const getNodeByPath = (path, nodes) => {
  return nodes.find(p => p.path === path);
};

export const getParentNode = (node, nodes) => {
  return getNodeByPath(getParentPath(node), nodes);
};

export const resolveHeaders = page => {
  const headers = groupHeaders(page.headers || []);
  return [
    {
      type: 'group',
      collapsable: false,
      title: page.title,
      children: headers.map(h => ({
        type: 'auto',
        title: h.title,
        basePath: page.path,
        path: page.path + '#' + h.slug,
        children: h.children || []
      }))
    }
  ];
};

/**
 * Top-level navigation model.
 *
 * The header exposes two selectors:
 *  - PRODUCTS: the company's product lines (what a visitor is looking for)
 *  - BACKEND_SECTIONS: the sub-sections of the open source backend, which used
 *    to live in the top navbar
 *
 * `match` holds the path prefixes that belong to the entry, used to highlight
 * the current one.
 */
export interface NavEntry {
  id: string;
  text: string;
  path: string;
  match: string[];
}

export const BACKEND_ID = 'backend';

export const PRODUCTS: NavEntry[] = [
  {
    id: 'iot-platform-4',
    text: 'Kuzzle IoT v4',
    path: '/iot-platform/4/',
    match: ['/iot-platform/4'],
  },
  {
    id: 'iot-platform',
    text: 'Kuzzle IoT v3',
    path: '/iot-platform/3/',
    match: ['/iot-platform/3'],
  },
  {
    id: BACKEND_ID,
    text: 'Kuzzle Backend v2',
    path: '/core/2/guides/',
    match: [
      '/core/',
      '/sdk/',
      '/official-plugins/',
      '/modules/',
      '/how-to/',
      '/v1',
      '/v2',
    ],
  },
  {
    id: 'paas',
    text: 'Kuzzle PaaS',
    path: '/paas-console/1/',
    match: ['/paas-console'],
  },
  {
    id: 'agent-ia',
    text: 'Kuzzle AI Agent',
    path: '/agent-ia/1/',
    match: ['/agent-ia'],
  },
];

export const BACKEND_SECTIONS: NavEntry[] = [
  {
    id: 'guide',
    text: 'Guide',
    path: '/core/2/guides/',
    match: ['/core/2/guides'],
  },
  { id: 'api', text: 'API', path: '/core/2/api/', match: ['/core/2/api'] },
  {
    id: 'framework',
    text: 'Framework',
    path: '/core/2/framework/',
    match: ['/core/2/framework'],
  },
  { id: 'sdk', text: 'SDKs', path: '/sdk/v2.html', match: ['/sdk/'] },
  {
    id: 'official-plugins',
    text: 'Plugins',
    path: '/official-plugins/v2.html',
    match: ['/official-plugins/'],
  },
  {
    id: 'modules',
    text: 'Modules',
    path: '/modules/v2.html',
    match: ['/modules/'],
  },
];

export const findEntry = (entries: NavEntry[], path: string) =>
  entries.find((entry) => entry.match.some((p) => path.startsWith(p)));

/**
 * Each section is served by its own VuePress instance, mounted on a base
 * (`/core/2/`, `/iot-platform/4/`...). The router paths are relative to that
 * base, while the entries above hold site-wide paths: prepend the base before
 * matching, otherwise nothing ever matches outside of the root instance.
 */
export const absolutePath = (base: string, path: string) =>
  `${(base || '/').replace(/\/$/, '')}${path}`;

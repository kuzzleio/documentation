/**
 * Top-level navigation model.
 *
 * The header exposes:
 *  - PRODUCTS: the company's product lines (what a visitor is looking for)
 *  - a top menu made of two groups, separated visually:
 *      - the product's own sections (`Product.sections`, e.g. Guide / API /
 *        Framework for the open source backend)
 *      - SHARED_SECTIONS, the ecosystem sections that apply to every product
 *        built on the backend (SDKs / Plugins / Modules). They stay in the
 *        menu whatever the product, as long as it opts in with
 *        `sharedSections`.
 *
 * Shared sections belong to no product: when browsing them, the product
 * shown is the one the visitor comes from (see `useCurrentProduct`), and the
 * open source backend when there is none.
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

export interface Product extends NavEntry {
  /** Sections specific to this product, first group of the top menu. */
  sections?: NavEntry[];
  /** Whether SHARED_SECTIONS are shown for this product. */
  sharedSections?: boolean;
  /**
   * Whether the product's documentation is reserved for customers. Display
   * only: access is enforced by CloudFront, whose `private_paths` list in
   * static-sites-infra must hold this product's path (ADR-0002, ADR-0004).
   */
  private?: boolean;
}

/** Entry of the home page catalog (ADR-0004). */
export interface CatalogEntry extends NavEntry {
  icon: string;
  description: string;
  private?: boolean;
}

export const BACKEND_ID = 'backend';

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
];

export const SHARED_SECTIONS: NavEntry[] = [
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

export const IOT_4_SECTIONS: NavEntry[] = [
  {
    id: 'guide',
    text: 'Guide',
    path: '/iot-platform/4/guides/',
    match: ['/iot-platform/4/guides'],
  },
  {
    id: 'api',
    text: 'API',
    path: '/iot-platform/4/api/',
    match: ['/iot-platform/4/api'],
  },
];

export const PRODUCTS: Product[] = [
  {
    id: 'iot-platform-4',
    text: 'Kuzzle IoT v4',
    path: '/iot-platform/4/',
    match: ['/iot-platform/4'],
    sections: IOT_4_SECTIONS,
    sharedSections: true,
    private: true,
  },
  {
    id: 'iot-platform',
    text: 'Kuzzle IoT v3',
    path: '/iot-platform/3/',
    match: ['/iot-platform/3'],
    sharedSections: true,
    private: true,
  },
  {
    id: BACKEND_ID,
    text: 'Kuzzle Backend v2',
    path: '/core/2/guides/',
    match: ['/core/', '/how-to/', '/v1', '/v2'],
    sections: BACKEND_SECTIONS,
    sharedSections: true,
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

/**
 * Tiles of the home page, IoT first: it is the product the site puts forward
 * (it used to be the landing page, see ADR-0004).
 */
export const CATALOG: CatalogEntry[] = [
  {
    id: 'iot-platform-4',
    text: 'Kuzzle IoT Platform',
    path: '/iot-platform/4/',
    match: ['/iot-platform/4'],
    icon: '/logos/device-manager.svg',
    description:
      'Digital twins, measure ingestion, alerts and permissions: build your IoT applications.',
    private: true,
  },
  {
    id: BACKEND_ID,
    text: 'Kuzzle Backend',
    path: '/core/2/guides/',
    match: ['/core/'],
    icon: '/logos/rocket.svg',
    description:
      'The open source backend: guides, API reference and framework.',
  },
  {
    id: 'paas',
    text: 'Kuzzle PaaS',
    path: '/paas-console/1/',
    match: ['/paas-console'],
    icon: '/logos/paas-logo.svg',
    description: 'Deploy your Kuzzle applications in a dedicated environment.',
  },
  {
    id: 'agent-ia',
    text: 'Kuzzle AI Agent',
    path: '/agent-ia/1/',
    match: ['/agent-ia'],
    icon: '/logos/agent-ia.svg',
    description:
      'A conversational agent for the IoT Platform: work with your data in natural language.',
  },
  {
    id: 'sdk',
    text: 'SDKs',
    path: '/sdk/v2.html',
    match: ['/sdk/'],
    icon: '/logos/brackets.svg',
    description: 'Client libraries for JavaScript, Dart, Java, C#, Go and more.',
  },
  {
    id: 'official-plugins',
    text: 'Plugins',
    path: '/official-plugins/v2.html',
    match: ['/official-plugins/'],
    icon: '/logos/plugin.svg',
    description: 'Official plugins that extend the backend.',
  },
  {
    id: 'modules',
    text: 'Modules',
    path: '/modules/v2.html',
    match: ['/modules/'],
    icon: '/logos/settings.svg',
    description: 'Ready-made modules for your Kuzzle applications.',
  },
];

export const findEntry = <T extends NavEntry>(entries: T[], path: string) =>
  entries.find((entry) => entry.match.some((p) => path.startsWith(p)));

export const BACKEND = PRODUCTS.find((p) => p.id === BACKEND_ID) as Product;

/** Whether a site-wide path belongs to a shared section rather than a product. */
export const isSharedPath = (path: string) =>
  Boolean(findEntry(SHARED_SECTIONS, path));

/** localStorage key remembering the last product the visitor browsed. */
export const PRODUCT_STORAGE_KEY = 'kuzzle-doc-product';

/**
 * Product to display for a site-wide path.
 *
 * `rememberedId` is the id of the last product browsed, used on shared
 * sections so that a visitor coming from IoT keeps IoT in the header while
 * reading the SDK pages. Falls back to the backend, which is the product the
 * shared sections historically belong to.
 */
export const resolveProduct = (
  path: string,
  rememberedId: string | null
): Product | undefined => {
  const own = findEntry(PRODUCTS, path);

  if (own || !isSharedPath(path)) {
    return own;
  }

  const remembered = PRODUCTS.find(
    (p) => p.id === rememberedId && p.sharedSections
  );

  return remembered || BACKEND;
};

/**
 * Groups of sections the top menu shows for a product: its own sections,
 * then the shared ones. Empty groups are dropped, so a product with neither
 * gets no top menu at all.
 */
export const topMenuGroups = (product?: Product): NavEntry[][] => {
  if (!product) {
    return [];
  }

  return [
    product.sections || [],
    product.sharedSections ? SHARED_SECTIONS : [],
  ].filter((group) => group.length > 0);
};

/**
 * Each section is served by its own VuePress instance, mounted on a base
 * (`/core/2/`, `/iot-platform/4/`...). The router paths are relative to that
 * base, while the entries above hold site-wide paths: prepend the base before
 * matching, otherwise nothing ever matches outside of the root instance.
 */
export const absolutePath = (base: string, path: string) =>
  `${(base || '/').replace(/\/$/, '')}${path}`;

/**
 * Path the client-side router knows for a site-wide path, or null when that
 * path belongs to another VuePress instance.
 *
 * Router paths are relative to the instance base, so `/core/2/api/` is known
 * to the backend instance as `/api/`. A path outside the current base can only
 * be followed with a full page load.
 */
export const routerPath = (base: string, path: string) => {
  const prefix = (base || '/').replace(/\/$/, '');

  if (!prefix) {
    return path;
  }

  return path.startsWith(`${prefix}/`) ? path.slice(prefix.length) : null;
};

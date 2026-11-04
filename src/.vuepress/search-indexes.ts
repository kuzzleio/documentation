import fs from 'node:fs';
import path from 'node:path';

import sections from './sections.json' with { type: 'json' };

/** A Pagefind index the search box merges, one per deployed VuePress instance. */
export interface SearchIndex {
  /** Site-wide path of the instance, with a trailing slash. */
  path: string;
  /** Label shown next to the results of this index. */
  label: string;
  /** Kuzzle major the instance documents, null when it spans several. */
  kuzzleMajor: number | null;
  /** Private section: only signed-in visitors can load its index. */
  private: boolean;
}

interface RepositoryEntry {
  deploy_path: string;
  doc_version: number;
  repo_name: string;
  private?: boolean;
  production?: boolean;
}

type SectionEntry = {
  section?: string;
  name?: string;
  version?: number;
  kuzzleMajor?: number;
};

const SECTIONS = sections as Record<string, SectionEntry>;

/** Sections the registry deploys without a sections.json entry. */
const FALLBACK_LABELS: Record<string, string> = {
  '/core/2': 'Kuzzle Backend v2',
  '/how-to/1': 'How-to',
  '/how-to/2': 'How-to',
};

const describe = (deployPath: string, repo: RepositoryEntry) => {
  const key = deployPath.replace(/\/$/, '');
  // sections.json keys come with or without the trailing slash
  const section = SECTIONS[key] || SECTIONS[`${key}/`];
  const major = /^\/(core|how-to)\/(\d+)/.exec(key);

  const label =
    FALLBACK_LABELS[key] ||
    (section?.name
      ? `${section.section === 'sdk' ? 'SDK ' : ''}${section.name}${
          section.version ? ` v${section.version}` : ''
        }`
      : repo.repo_name);

  return {
    label,
    kuzzleMajor: section?.kuzzleMajor ?? (major ? Number(major[2]) : null),
  };
};

/**
 * Lists the indexes of every instance deployed alongside this one, read from
 * .repos/repositories.json. kuzdoc runs every build from the framework root,
 * the root instance as well as each section, so the registry is always at
 * the same place relative to the working directory.
 *
 * `withStagingOnly`: include the entries with `"production": false`, which
 * the production deploy skips (their index would answer 404).
 */
export const listSearchIndexes = (withStagingOnly: boolean): SearchIndex[] => {
  const registry = path.resolve(process.cwd(), '.repos/repositories.json');
  const repos: RepositoryEntry[] = fs.existsSync(registry)
    ? JSON.parse(fs.readFileSync(registry, 'utf8'))
    : [];

  const indexes = repos
    .filter((repo) => withStagingOnly || repo.production !== false)
    .map((repo) => {
      const deployPath = repo.deploy_path.endsWith('/')
        ? repo.deploy_path
        : `${repo.deploy_path}/`;

      return {
        path: deployPath,
        ...describe(deployPath, repo),
        private: repo.private === true,
      };
    });

  return [
    { path: '/', label: 'Kuzzle', kuzzleMajor: null, private: false },
    ...indexes,
  ];
};

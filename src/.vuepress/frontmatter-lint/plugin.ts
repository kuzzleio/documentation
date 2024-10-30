import { Logger } from '@vuepress/helper';
import { writeFileSync } from 'fs';
import { pickBy } from 'lodash-es';
import mm from 'micromatch';
import { PluginFunction } from 'vuepress';

import { FrontmatterLintPluginOptions, LintError } from './types';
import { appendFixes } from './append-fixes';

export const PLUGIN_NAME = '@kuzzleio/vuepress-plugin-frontmatter-lint';
const logger = new Logger(PLUGIN_NAME);

export const DEFAULT_DUMP_FILE = './frontmatter-lint-errors.json';
export const DEFAULT_EXCLUDE = ['/404.html'];

function addError(errors: Record<string, LintError[]>, path: string, error: LintError) {
  if (!errors[path]) {
    errors[path] = [];
  }

  errors[path].push(error);
}

function generateConsoleReport(errors: Record<string, LintError[]>) {
  logger.error('Some pages do not have a valid frontmatter.');

  Object.keys(errors).forEach(path => {
    const errorsOnFile = errors[path];
    logger.error(`- ${path}`);

    errorsOnFile.forEach(e => {
      if (e.error === 'EMPTY_KEY') {
        logger.error(`  ${e.error}`);
      } else {
        logger.error(`  ${e.error} on field "${e.key}"`);

        if (e.error === 'INVALID_TYPE' || e.error === 'INVALID_VALUE') {
          logger.error(`    Expected: ${e.expected}`);
          logger.error(`    Got: ${e.got}`);
        }
      }
    });
  });
}

function dumpErrorsToFile(errors: Record<string, LintError[]>, fileName: string, sourceDir: string) {
  writeFileSync(fileName, JSON.stringify(errors, null, 4));

  logger.info(`Frontmatter errors have been dumped to ${fileName}`);
  logger.info('You might consider running the auto-fixer with the following command');
  logger.info(`  $(npm bin)/frontmatter-fix -e ${fileName} -d ${sourceDir}`);
}

export const frontmatterLintPlugin =
  (options: FrontmatterLintPluginOptions): PluginFunction =>
  (app) => {
    const { specs } = options;
    const exclude = options.exclude || DEFAULT_EXCLUDE;
    let errorsByPath: Record<string, LintError[]> = {};

    return {
      name: PLUGIN_NAME,
      extendsPage(page) {
        const {
          path,
          frontmatter,
        } = page;

        const matchesExclude = mm([path], exclude);

        if (matchesExclude.length) {
          return;
        }

        const requiredFields = pickBy(specs, s => s.required === true);
        Object.keys(requiredFields).forEach(fieldName => {
          if (!Object.keys(frontmatter).includes(fieldName)) {
            addError(errorsByPath, path, {
              error: 'MISSING_KEY',
              key: fieldName
            });
          }
        });

        Object.keys(frontmatter).forEach(key => {
          if (!key) {
            addError(errorsByPath, path, {
              error: 'EMPTY_KEY'
            });

            return;
          }

          if (!(key in specs)) {
            addError(errorsByPath, path, {
              error: 'INVALID_KEY',
              key
            });

            return;
          }

          const value = frontmatter[key];
          if (value === null || typeof value === 'undefined') {
            addError(errorsByPath, path, {
              error: 'EMPTY_VALUE',
              key
            });

            return;
          }

          const spec = specs[key];
          if (typeof value !== spec.type && (spec.type !== 'array' || !Array.isArray(value))) {
            addError(errorsByPath, path, {
              error: 'INVALID_TYPE',
              key,
              expected: spec.type,
              got: typeof value
            });

            return;
          }

          const valueAsString = String(value);
          if ('allowedValues' in spec) {
            if (spec.allowedValues?.includes(valueAsString) === false) {
              addError(errorsByPath, path, {
                error: 'INVALID_VALUE',
                key,
                expected: JSON.stringify(spec.allowedValues),
                got: valueAsString
              });
            }
          }
        });
      },
      onWatched() {
        if (Object.keys(errorsByPath).length) {
          generateConsoleReport(errorsByPath);
        }
      },
      onPrepared() {
        errorsByPath = appendFixes(errorsByPath, app);

        if (Object.keys(errorsByPath).length) {
          generateConsoleReport(errorsByPath);

          if (options.dumpToFile) {
            dumpErrorsToFile(
              errorsByPath,
              options.dumpFile || DEFAULT_DUMP_FILE,
              app.dir.source()
            );
          }

          errorsByPath = {};
          if (options.abortBuild) {
            logger.error('Aborting build due to frontmatter lint errors.');
            process.exit(1);
          }
        }
      }
    };
  }

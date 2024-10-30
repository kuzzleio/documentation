export type CommonSpec = {
  required: boolean;
};

export type StringSpec = {
  type: 'string';
  allowedValues?: string[];
};

export type NumberSpec = {
  type: 'number';
};

export type BooleanSpec = {
  type: 'boolean';
};

export type ArraySpec = {
  type: 'array';
};

export type Spec = CommonSpec & (StringSpec | NumberSpec | BooleanSpec | ArraySpec);

export type FrontmatterLintPluginOptions = {
  abortBuild: boolean;
  exclude?: string[];
  dumpFile?: string;
  dumpToFile: boolean;
  specs: Record<string, Spec>;
};

export type MissingKeyLintError = {
  error: 'MISSING_KEY';
  key: string;
};

export type EmptyKeyLintError = {
  error: 'EMPTY_KEY';
};

export type InvalidKeyLintError = {
  error: 'INVALID_KEY';
  key: string;
};

export type EmptyValueLintError = {
  error: 'EMPTY_VALUE';
  key: string;
};

export type InvalidTypeLintError = {
  error: 'INVALID_TYPE';
  key: string;
  expected: string;
  got: string;
};

export type InvalidValueLintError = {
  error: 'INVALID_VALUE';
  key: string;
  expected: string;
  got: string;
};

export type LintError =
  | MissingKeyLintError
  | EmptyKeyLintError
  | InvalidKeyLintError
  | EmptyValueLintError
  | InvalidTypeLintError
  | InvalidValueLintError;

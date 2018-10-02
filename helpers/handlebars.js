const
  minimatch = require('minimatch'),
  { SafeString } = require('handlebars');

module.exports = {
  not: v => !v,

  eq: (v1, v2) => v1 === v2,

  ne: (v1, v2) => v1 !== v2,

  lt: (v1, v2) => v1 < v2,

  gt: (v1, v2) => v1 > v2,

  lte: (v1, v2) => v1 <= v2,

  gte: (v1, v2) => v1 >= v2,

  and: (v1, v2) => v1 && v2,

  or: (v1, v2) => v1 || v2,

  startwith: (str, substr) => {
    if (!str) {
      return false;
    }
    return str.startsWith(substr);
  },

  mstartwith: (str, ...args) => {
    if (!str) {
      return false;
    }

    return args.some(arg => str.startsWith(arg));
  },

  endswith: (str, substr) => {
    return str.endsWith(substr);
  },

  firstDefinedOf: (...args) => {
    return args.find(a => a);
  },

  currentYear: () => {
    return new Date().getFullYear();
  },

  dateToISO: (d) => {
    if (d instanceof Date) {
      return d.toISOString();
    }
    return d;
  },

  displayTitle: (data, options) => {
    let title = data.title;
    const config = options.data.root;

    if (config.is_dev && config.exclude.some(e => minimatch(data.src, `**/${e}/**`))) {
      title = `<div style='color:red'>(!) ${title}</div>`;
    }

    return new SafeString(title);
  },

  since: version => `<p class="since">Added in v${version}</p>`,

  deprecated: version => `<p class="deprecated">Deprecated since v${version}</p>`
};


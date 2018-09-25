const handelbarsHelpers = {
  not: (v) => {
    return !v;
  },
  
  eq: (v1, v2) => {
    return v1 === v2;
  },
  
  ne: (v1, v2) => {
    return v1 !== v2;
  },
  
  lt: (v1, v2) => {
    return v1 < v2;
  },
  
  gt: (v1, v2) => {
    return v1 > v2;
  },
  
  lte: (v1, v2) => {
    return v1 <= v2;
  },
  
  gte: (v1, v2) => {
    return v1 >= v2;
  },
  
  and: (v1, v2) => {
    return v1 && v2;
  },
  
  or: (v1, v2) => {
    return v1 || v2;
  },
  
  startwith: (str, substr) => {
    if (!str) {
      return false;
    }
    return str.startsWith(substr);
  },
  
  mstartwith: () => {
    var args = Array.prototype.slice.call(arguments);
    var str = args.shift();
    if (!str) {
      return false;
    }
    return args.reduce((found, currentStr) => {
      return found || str.startsWith(currentStr);
    }, false);
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
  }
};

module.exports = handelbarsHelpers;
// markdown-it plugin for:
// 1. adding target="_blank" to external links
// 2. converting internal links to <router-link>

const url = require('url')

const indexRE = /(^|.*\/)(index|readme).md(#?.*)$/i

module.exports = (configuration) => {
  return (md) => {
    let hasOpenRouterLink = false
    let hasOpenExternalLink = false

    md.renderer.rules.link_open = (tokens, idx, options, env, self) => {
      const { base } = configuration
      const { relativePath } = env
      const token = tokens[idx]
      const hrefIndex = token.attrIndex('href')
      if (hrefIndex >= 0) {
        const link = token.attrs[hrefIndex]
        const href = link[1]
        const isExternal = /^https?:/.test(href)
        const startsWithBaseURL = href.startsWith(base)
        
        if (isExternal) {
          hasOpenExternalLink = true
          token.attrSet('target', '_blank')
          token.attrSet('rel', 'noopener noreferrer')
        } else if (startsWithBaseURL) {
          hasOpenRouterLink = true
          tokens[idx] = toRouterLink(token, link, relativePath, base)
        }
      }
      return self.renderToken(tokens, idx, options)
    }

    function toRouterLink(token, link, relativePath, base) {
      link[0] = 'to'
      let to = link[1].replace(base, '')

      // convert link to filename and export it for existence check
      const links = md.$data.links || (md.$data.links = [])
      links.push(to)

      // relative path usage.
      if (!to.startsWith('/')) {
        to = relativePath
          ? url.resolve('/' + relativePath, to)
          : ensureBeginningDotSlash(to)
      }

      const indexMatch = to.match(indexRE)
      if (indexMatch) {
        const [, path, , hash] = indexMatch
        to = path + hash
      } else {
        to = to
          .replace(/\.md$/, '.html')
          .replace(/\.md(#.*)$/, '.html$1')
      }

      // markdown-it encodes the uri
      link[1] = decodeURI(to)

      // export the router links for testing
      const routerLinks = md.$data.routerLinks || (md.$data.routerLinks = [])
      routerLinks.push(to)

      return Object.create(token, {
        tag: { value: 'router-link' }
      })
    }

    md.renderer.rules.link_close = (tokens, idx, options, env, self) => {
      const token = tokens[idx]
      if (hasOpenRouterLink) {
        token.tag = 'router-link'
        hasOpenRouterLink = false
      }
      if (hasOpenExternalLink) {
        hasOpenExternalLink = false
        // add OutBoundLink to the beforeend of this link if it opens in _blank.
        return '<OutboundLink/>' + self.renderToken(tokens, idx, options)
      }
      return self.renderToken(tokens, idx, options)
    }
  }
}

function ensureBeginningDotSlash(path) {
  if (path.startsWith('./')) {
    return path
  }
  return './' + path
}

import MarkdownIt from 'markdown-it';

// Header of the column that takes the remaining width in API tables
// (arguments, options, properties...). Other tables keep the automatic layout.
const STRETCHED_HEADER = 'description';

const apiTable = (md: MarkdownIt) => {
  md.core.ruler.push('api_table', (state) => {
    const tokens = state.tokens;

    for (let i = 0; i < tokens.length; i++) {
      if (tokens[i].type !== 'th_open') {
        continue;
      }

      const content = tokens[i + 1]?.type === 'inline' ? tokens[i + 1].content : '';

      if (content.trim().toLowerCase() === STRETCHED_HEADER) {
        tokens[i].attrSet('data-stretch', '');
      }
    }
  });
};

export default apiTable;

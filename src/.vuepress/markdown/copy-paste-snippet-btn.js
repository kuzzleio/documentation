module.exports = md => {
  const fence = md.renderer.rules.fence;
  md.renderer.rules.fence = (...args) => {
    const [tokens, idx] = args;
    const rawCode = fence(...args);
    const codeWithButton = rawCode.replace(
      '<!--afterbegin-->',
      `${renderButton(idx)}<!--afterbegin-->`
    );
    return codeWithButton;
  };
};

function renderButton(index) {
  return `
    <div class="codehilite" id="__code_${index}">
      <button
        class="md-clipboard"
        title="Copy to clipboard"
        data-clipboard-target="#__code_${index} pre code"
      />
      <span class="md-clipboard__message">Copied to clipboard!</span>
    </div>`;
}

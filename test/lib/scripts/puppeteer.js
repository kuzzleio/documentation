const 
  // puppeteer is globally installed in the container
  puppeteer = require('/usr/local/lib/node_modules/puppeteer'), 
  renderedSnippetPath = process.argv[2];

const runInBrowser = async (snippetPath) => {
  try {
    const 
      browser = await puppeteer.launch({
        dumpio: true,
        args: [
          '--no-sandbox',
        ]
      }),
      page = await browser.newPage();
    
    page.on('error', err => {
      console.error(err);
    });
    
    page.on('pageerror', err => {
      console.error(err);
    });
    
    await page.goto(`file:${snippetPath}`, {
      waitUntil: 'networkidle0'
    });
    
    await browser.close();
  } catch (error) {
    console.error(error);
  }
};

runInBrowser(renderedSnippetPath);
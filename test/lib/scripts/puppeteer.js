const 
  puppeteer = require('puppeteer'),
  renderedSnippetPath = process.argv[2];

const runInBrowser = async (renderedSnippetPath) => {
  try {
    const 
      browser = await puppeteer.launch({
        args: [
          '--no-sandbox',
        ],
        dumpio: false,
      });
      page = await browser.newPage();
    
    page.on('console', message => {
      if (message.type() !== 'error') {
        console.log(message.text());
      } else {
        console.error(message.text());
      }
    });
    
    page.on('error', err => {
      console.error(err);
    });
    
    page.on('pageerror', err => {
      console.error(err);
    });
    
    await page.goto(`file:${renderedSnippetPath}`, {
      waitUntil: 'networkidle0'
    });
    
    await browser.close();
  } catch (error) {
    console.error(error);
  }
}

runInBrowser(renderedSnippetPath)
const 
  puppeteer = require('puppeteer'),
  path = require('path');

const runInBrowser = async (renderedSnippetPath) => {
  try {
    const 
      browser = await puppeteer.launch(),
      page = await browser.newPage();

    page.on('console', log => {
      console.log(log._text);
    });
    
    page.on('error', err => {
      console.error(err._text);
    });
    
    page.on('pageerror', err => {
      console.error(err._text);
    });
    
    
    await page.goto(`file:${renderedSnippetPath}`, {
      waitUntil: 'networkidle0'
    });

    await browser.close()  
  } catch (error) {
    console.error(error);
  }
  
}

const renderedSnippetPath = process.argv[0];

runInBrowser(renderedSnippetPath);
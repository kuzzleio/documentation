const express = require('express');
const path = require('path');
const app = express();

const reportsFolder = path.join(__dirname, '../../../reports/')

app.use(express.static(reportsFolder));

app.get('/reports', function (req, res) {
  res.sendFile(reportsFolder + 'index.html');
})

app.listen(3001);

console.log('Go to "http://localhost:3001/reports" to see the last report');
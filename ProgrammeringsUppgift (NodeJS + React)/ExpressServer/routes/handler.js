const express = require('express');
const fs = require('fs');
const {
  BetalningsServiceHandler,
} = require('../controllers/BetalningsServiceHandler');
const {
  InbetalningstjanstenHandler,
} = require('../controllers/InbetalningstjanstenHandler');

const router = express.Router();

function uploadFile(upload) {
  // Define the route for file upload
  router.post('/upload', upload.single('file'), (req, res) => {
    if (req.file) {
      // File was uploaded
      const { originalname, path } = req.file;

      if (originalname.endsWith('_betalningsservice.txt')) {
        try {
          const parsed = BetalningsServiceHandler(path);
          res.status(200).json(parsed); // Send parsed data as JSON response
        } catch (error) {
          console.error(error);
          res.status(500).json({ error: 'An error occurred during parsing.' }); // Send error response as JSON
        }
      } else if (originalname.endsWith('_inbetalningstjansten.txt')) {
        try {
          const parsing = InbetalningstjanstenHandler(path);
          res.status(200).json(parsing); // Send parsed data as JSON response
        } catch (error) {
          console.error(error);
          res.status(500).json({ error: 'An error occurred during parsing.' }); // Send error response as JSON
        }
      } else {
        // File name doesn't match
        fs.unlinkSync(path); // Delete the file
        res.status(400).json({ error: 'File is not available.' }); // Send error response as JSON
      }
    } else {
      // No file was uploaded
      res.status(400).json({ error: 'No file selected.' }); // Send error response as JSON
    }
  });

  return router;
}

module.exports = { uploadFile };

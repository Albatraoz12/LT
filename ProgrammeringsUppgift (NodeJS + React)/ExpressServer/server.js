const express = require('express');
const multer = require('multer');
var cors = require('cors');

const routes = require('./routes/handler'); // Finds all the route logic

const app = express(); //Initialize the express server

app.use(express.json()); // Required if you want to parse JSON requests

// Accepts requests from localhost:3000.
app.use(
  cors({
    credentials: true,
    origin: ['http://localhost:3000'],
  })
);

// Set up multer for file uploads
const storage = multer.diskStorage({
  destination: 'uploads/',
  filename: (req, file, cb) => {
    cb(null, file.originalname); // Set the filename to be the original name
  },
});

const upload = multer({ storage });

// uses uploadFile function from routes file and pass the upload variable
app.use('/api', routes.uploadFile(upload));

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

// Starts the server/api
const port = 8080;
app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});

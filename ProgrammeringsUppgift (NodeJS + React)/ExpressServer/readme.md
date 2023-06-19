# Hello

Welcome to my api for Lumera. This is an ExpressJs (NodeJS) api to handle payment file that come in to Lumera. The purpose of this api is to decode the file and then send it back to the client or can be used to store into a Database.

My api will take in a file, save it into a folder and then a controller will pick up the file and decode it to later send as a respond to the client with the information of the file.

I have used PaymentReceiver interface which i have been given from Lumera to use, this can be change to store the data into a databse or an other file.

# How to use the api

You need to first have Node installed on your computer. Open up this project and open up the terminal och navigate with the terminal so you are on the root of this project.

First install the dependencys by doing npm i. then to start and serve the projekt you need to type npm run start or npm start.

You will need to use an api caller like postman or insomia. I Recoment using Postman, you can create a new POST api call to the url: http://localhost:8080/api/upload and in Body choose form-data, key should be file and the value should be file and then choose a file to send in.

You can also use the client that i have provided into this folder caleed react-front-end. it is a react project a simple frontend to send a file and show the reults. to get the frontend started you need to navigate to react-front-end root folder and type npm run start.

In order to have the frontend talk with the backend both of them needs to be served(started).

The server will be served on localhost:8080/ and the frontend will be served on localhost:3000

# Structure

## Controllers

Here i store the controllers (logic) on what the api should do in this project the controllers should decode 2 diffrent files and depending on which file the handler will controll what controllers should be used for the file coming in.

## Handler

Takes care of what controller should be used when a file is sent in. If no valid file is being sent in the client will get a 500 response with a message.

## Server.js

Open up a server with so that the api is live and ready to be used.

## Interface

as the maps says, i store my interface object to use in the controllers to find the information needed and send it to the client.

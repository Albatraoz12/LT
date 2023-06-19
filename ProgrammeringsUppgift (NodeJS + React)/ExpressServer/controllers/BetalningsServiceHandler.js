const fs = require('fs');
const path = require('path');
const PaymentReceiver = require('../interface/PaymentReceiver');

/**
 * Parses a file containing payment records in a specific format.
 * @param {string} file_path - The path to the file to parse.
 * @returns {Object} - The parsed payment bundle object.
 */
function BetalningsServiceHandler(file_path) {
  try {
    const fileContent = fs.readFileSync(file_path, 'utf-8');
    const fileLines = fileContent.split('\r\n');

    // Öppningspost (Opening Post)
    const line = fileLines[0];
    const accountNumber = line.substring(1, 16);
    const paymentDateStr = line.substring(40, 48);
    const paymentDate = parseDate(paymentDateStr);
    const formattedPaymentDate = formatDate(paymentDate);
    const currency = line.substring(48, 51);

    PaymentReceiver.startPaymentBundle(
      accountNumber,
      formattedPaymentDate,
      currency
    );

    // Checking the correct number of records
    const poster = line.substring(30, 40).trim();
    if (fileLines.length - 2 !== parseInt(poster)) {
      throw new Error(
        'antal poster stämmer inte (incorrect number of records)'
      );
    }
    // Betalningspost (Payment Post)
    // Loop through the file from index 1 to the second-to-last line
    for (let i = 1; i < fileLines.length - 1; i++) {
      try {
        const amount = parseFloat(
          fileLines[i].substring(1, 15).replace(',', '.')
        );
        const reference = fileLines[i].substring(16, 51).trim();
        PaymentReceiver.payment(amount, reference);
      } catch (error) {
        console.log('Error parsing line:', i);
      }
    }

    // Return the payment bundle object to the Handler
    const paymentBundle = PaymentReceiver.endPaymentBundle();
    return paymentBundle;
  } catch (error) {
    console.error('Error parsing file:', file_path);
    console.error(error);
    throw error;
  }
}

/**
 * Parses a date string and returns a Date object.
 * @param {string} dateStr - The date string to parse.
 * @returns {Date} - The parsed Date object.
 */
function parseDate(dateStr) {
  const year = dateStr.substring(0, 4);
  const month = dateStr.substring(4, 6);
  const day = dateStr.substring(6, 8);
  return new Date(year, month - 1, day);
}

/**
 * Formats a Date object into a specific date format.
 * @param {Date} date - The Date object to format.
 * @returns {string} - The formatted date string.
 */
function formatDate(date) {
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  return `${year}${month}${day}`;
}

module.exports = { BetalningsServiceHandler };

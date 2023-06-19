const fs = require('fs');
const PaymentReceiver = require('../interface/PaymentReceiver');

/**
 * Parses a file containing payment records.
 * @param {string} filename - The name of the file to parse.
 * @returns {Object} - The parsed payment bundle object.
 */
function InbetalningstjanstenHandler(filename) {
  try {
    // Read the file content
    const content = fs.readFileSync(filename, { encoding: 'latin1' });
    const lines = content.split('\r\n'); // Split the content into lines

    const openingType = lines[0];
    const kontonummer = parseInt(openingType.substring(14, 24));
    const paymentDate = new Date();

    // Start the payment bundle, using the provided account number, payment date (today's date), and currency (SEK).
    PaymentReceiver.startPaymentBundle(kontonummer, paymentDate, 'SEK');

    // Loop through the rest of the lines, excluding the first and the last one.
    for (let i = 1; i < lines.length - 2; i++) {
      const line = lines[i];
      const bp = line.substring(2, 22).trim();

      try {
        const blp =
          bp.substring(0, bp.length - 2).trim() +
          '.' +
          bp.substring(bp.length - 2);
        const belopp = parseFloat(blp.replace(',', '.')).toFixed(2);
        const ref = line.substring(40, 65).trim();
        PaymentReceiver.payment(belopp, ref);
      } catch (error) {
        throw new Error(`Error creating belopp. Line: ${i}`);
      }
    }

    // Finds the last post and sends it back to the handler.
    // This can be further developed with additional logic if needed.
    const endPost = lines.length - 2;
    const parseEndLine = lines[endPost];

    // Return the payment bundle object to the client
    const endPayment = PaymentReceiver.endPaymentBundle(parseEndLine);
    return endPayment;
  } catch (error) {
    console.error(error);
  }
}

module.exports = { InbetalningstjanstenHandler };

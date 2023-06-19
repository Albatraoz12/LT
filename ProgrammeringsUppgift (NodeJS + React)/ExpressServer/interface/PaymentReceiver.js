const PaymentReceiver = {
  paymentBundle: null,

  // Function to start a new payment bundle
  startPaymentBundle(accountNumber, paymentDate, currency) {
    this.paymentBundle = {
      accountNumber,
      paymentDate,
      currency,
      payments: [],
    };
  },

  // Function to add a payment to the current payment bundle
  payment(amount, reference) {
    if (this.paymentBundle) {
      this.paymentBundle.payments.push({ amount, reference });
    }
  },

  // Function to end the current payment bundle and return the result
  endPaymentBundle(endPost) {
    if (this.paymentBundle) {
      if (endPost) {
        this.paymentBundle.endPost = endPost;
      }
      const result = this.paymentBundle;
      this.paymentBundle = null;
      return result;
    }
    return null;
  },
};

module.exports = PaymentReceiver;

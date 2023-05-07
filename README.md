# API Documentation

This README file provides documentation for the Payments API.

## API Description

The Payments API allows you to retrieve information about a specific SEP-0031 payment. The API endpoint to retrieve payment information is as follows:

GET /api/v2/payments/{payment_id}/sep0031/info


To retrieve the payment information, you need to replace `{payment_id}` with the unique identifier of the payment in UUID format.

### API Endpoint

https://payments-api.armenotech.dev/api/v2/payments/edeab824-178e-4fd7-9bf0-bd88a6fd114a/sep0031/info?countries=WWC&asset=ATUSD


The above endpoint retrieves information about a specific SEP-0031 payment. It includes the following parameters:

- `countries`: A string parameter specifying a list of countries where the payment method is available. The value "WWC" indicates "worldwide coverage".
- `asset`: A string parameter that specifies the asset for the payment method. The value "ATUSD" indicates "USD asset".

### Example Request

curl --location 'https://payments-api.armenotech.dev/api/v2/payments/edeab824-178e-4fd7-9bf0-bd88a6fd114a/sep0031/info?countries=WWC&asset=ATUSD'

The above command makes a GET request to the API endpoint to retrieve payment information for the specified payment ID, countries, and asset.

### Example Response

The API response will contain information about the SEP-0031 payment, including details such as payment status, amount, sender information, receiver information, etc. The response format may vary depending on the API implementation. Here's an example response:

```json
{
  "payment_id": "edeab824-178e-4fd7-9bf0-bd88a6fd114a",
  "status": "completed",
  "amount": "100.00",
  "currency": "USD",
  "sender": {
    "name": "John Doe",
    "account_number": "1234567890"
  },
  "receiver": {
    "name": "Jane Smith",
    "account_number": "9876543210"
  }
}
```
Running Tests
To run tests for the Payments API, execute the following command:

mvn test

or run runTest.bat file

This command will run the tests and provide the test results and any potential errors or failures.

Please ensure that you have the necessary environment setup and dependencies installed before running the tests.

# Bank API Kata

This project is a minimalist banking API exposing services for deposit, withdrawal, and transaction history management.

---

## REST API Endpoints
### Deposit ###
- **URL:**
  `http://localhost:8081/deposit?amount={amount}`
- **Example:**
  `http://localhost:8081/deposit?amount=200`
- **Respone:**
  `Deposit of 200 EUR was successful.`

---

### Withdraw ###
- **URL:**
  `http://localhost:8081/withdraw?amount={amount}`
- **Example:**
  `http://localhost:8081/deposit?amount=100`
- **Respone:**
  `Withdraw of 100 EUR was successful.`

---

### Print statement ###
- **URL:**
  `http://localhost:8081/statement`

import logging

# Setup logging
logging.basicConfig(level=logging.DEBUG, format="%(levelname)s: %(message)s")

def parse_transactions(raw_data):
    transactions = []
    for index, record in enumerate(raw_data):
        parts = record.split(",")
        if len(parts) < 4:
            logging.warning(f"Skipping malformed record at index {index}: {record}")
            continue
        try:
            amount_str = parts[1].strip()
            if amount_str == "":
                logging.warning(f"Missing amount in record at index {index}: {record}")
                continue

            transaction = {
                "date": parts[0].strip(),
                "amount": float(amount_str),
                "type": parts[2].strip().lower(),
                "description": parts[3].strip() if len(parts) > 3 else "N/A"
            }
            transactions.append(transaction)
        except ValueError as ve:
            logging.error(f"Error parsing amount in record at index {index}: {record} | {ve}")
    logging.debug(f"Parsed {len(transactions)} transactions.")
    return transactions


def calculate_balance(transactions):
    balance = 0
    for txn in transactions:
        if txn["type"] == "credit":
            balance += txn["amount"]
        elif txn["type"] == "debit":
            balance -= txn["amount"]
        else:
            logging.warning(f"Unknown transaction type: {txn['type']}")
    return round(balance, 2)


def generate_summary(transactions):
    credit_txns = [t for t in transactions if t["type"] == "credit"]
    debit_txns = [t for t in transactions if t["type"] == "debit"]
    
    credit_count = len(credit_txns)
    debit_count = len(debit_txns)
    
    total_credit_amount = sum(t["amount"] for t in credit_txns)
    average_credit = total_credit_amount / credit_count if credit_count > 0 else 0

    # Bug fix: key was 'ammount', corrected to 'amount'
    try:
        largest_txn = max(transactions, key=lambda t: t["amount"])
    except ValueError:
        largest_txn = {"description": "None", "amount": 0.0}

    summary = {
        "credits": credit_count,
        "debits": debit_count,
        "average_credit": round(average_credit, 2),
        "largest_txn": largest_txn
    }
    logging.debug("Generated transaction summary.")
    return summary


def main():
    raw_data = [
        "2025-07-01, 1200, CREDIT, Salary",
        "2025-07-02, 300, debit, Grocery",
        "2025-07-03, , debit, Restaurant",      # Missing amount
        "2025-07-04, 200, DEBIT",               # Missing description
        "2025-07-05, 400, credit, Freelance, Bonus"  # Extra comma
    ]

    logging.info("Starting transaction parser.")
    transactions = parse_transactions(raw_data)

    logging.info("Calculating balance.")
    balance = calculate_balance(transactions)

    logging.info("Generating summary.")
    summary = generate_summary(transactions)

    print("Final balance:", balance)
    print("Summary:")
    print("Credits:", summary["credits"])
    print("Debits:", summary["debits"])
    print("Avg Credit:", summary["average_credit"])
    print("Largest Txn:", summary["largest_txn"]["description"], "-", summary["largest_txn"]["amount"])


if __name__ == "__main__":
    main()

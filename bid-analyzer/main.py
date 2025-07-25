from app.db import fetch_data
from app.analyzer import analyze_bids
from app.writer import save_analysis

def main():
    raw_data = fetch_data("SELECT id AS bid_id, budget, category_code FROM bidding_notice WHERE is_successful IS NULL")
    results = analyze_bids(raw_data)
    save_analysis(results)

if __name__ == "__main__":
    main()
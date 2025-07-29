from app.db import execute_query

def save_analysis(results: list[dict]):
    insert_query = """
    INSERT INTO bid_analysis (bid_id, predicted_success)
    VALUES (:bid_id, :predicted_success)
    ON CONFLICT (bid_id) DO UPDATE SET predicted_success = EXCLUDED.predicted_success
    """
    for row in results:
        execute_query(insert_query, row)

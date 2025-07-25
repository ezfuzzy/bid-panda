from sqlalchemy import create_engine, text
from app.config import DB_URL

engine = create_engine(DB_URL)

def fetch_data(query: str):
    with engine.connect() as conn:
        result = conn.execute(text(query))
        return [dict(row) for row in result]

def execute_query(query: str, params=None):
    with engine.connect() as conn:
        conn.execute(text(query), params or {})
        conn.commit()
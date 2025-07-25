import pandas as pd
import joblib
from app.preprocess import preprocess_input
import os

MODEL_PATH = os.path.join(os.path.dirname(__file__), "model/bid_model.pkl")

def analyze_bids(data: list[dict]) -> list[dict]:
    df = pd.DataFrame(data)

    model = joblib.load(MODEL_PATH)
    X = preprocess_input(df)

    df['predicted_success'] = model.predict(X)
    return df[['bid_id', 'predicted_success']].to_dict(orient='records')
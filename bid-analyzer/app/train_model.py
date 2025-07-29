import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import joblib
from app.db import fetch_data
import os

MODEL_PATH = os.path.join(os.path.dirname(__file__), "model/bid_model.pkl")

def train_model():
    # 이거 테이블이랑 컬럼명 바꿔야함
    raw_data = fetch_data("SELECT budget, category_code, is_successful FROM bidding_notice WHERE is_successful IS NOT NULL")
    
    df = pd.DataFrame(raw_data)

    X = df[['budget', 'category_code']]
    y = df['is_successful']

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)
    clf = RandomForestClassifier()
    clf.fit(X_train, y_train)

    y_pred = clf.predict(X_test)
    print("Accuracy:", accuracy_score(y_test, y_pred))

    os.makedirs(os.path.dirname(MODEL_PATH), exist_ok=True)
    joblib.dump(clf, MODEL_PATH)
    print(f"모델 저장 완료: {MODEL_PATH}")

if __name__ == "__main__":
    train_model()
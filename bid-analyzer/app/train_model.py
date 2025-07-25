import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import joblib
from app.db import fetch_data
import os

MODEL_PATH = os.path.join(os.path.dirname(__file__), "model/bid_model.pkl")
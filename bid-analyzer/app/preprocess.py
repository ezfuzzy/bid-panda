import pandas as pd

def preprocess_input(df: pd.DataFrame) -> pd.DataFrame:
    df = df.copy()
    df['category_code'] = df['category_code'].astype('category').cat.codes
    return df[['budget', 'category_code']]
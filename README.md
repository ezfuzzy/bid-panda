<img width="960" height="260" alt="Image" src="https://github.com/user-attachments/assets/78c4a65e-4891-465f-be9b-a5cffa1c412d" />

# bid-panda

> From API to AI — a full pipeline for publishing and analyzing job & bidding data.

**bid-panda** is an automated system that collects job and bidding data, stores it, analyzes it, and auto-publishes selected information to Cafe — helping users stay ahead in upcoming bidding opportunities.

---

## 🚀 Features

### 📡 1. API & Crawling
- **Scheduled data collection** from major platforms:
  - **KONEPS (g2b)** via API
  - **Albamon** & **Albacheonguk** via web crawling using `jsoup`

### 🗃 2. Data Processing & DB Integration
- **Database**: PostgreSQL
- **Tables**:
  - `BiddingNotice` (from KONEPS)
  - `Job` (from Albamon & Albacheonguk)

### 📝 3. Auto Publishing
- **Platform**: Naver Cafe
- **Tool**: Selenium
- **Function**: Automatically posts formatted content to the cafe

### 🤖 4. AI-based Analysis _(TBD)_
- Planned tools:
  - `scikit-learn`
  - `XGBoost`
- Goal: Predict and identify high-potential bidding notices

### 📊 5. Data Visualization _(TBD)_
- Possible libraries:
  - `Recharts`
  - `Chart.js`
  - `Plotly`
  - `D3.js`
- Purpose: Visualize insights and trends for strategic decisions

---

## 📂 Tech Stack

| Area           | Tools / Frameworks         |
|----------------|-----------------------------|
| Language       | Python, Java (for api/crawling) |
| DB             | PostgreSQL                  |
| Web Crawling   | Jsoup(TBD)                       |
| Automation     | Selenium                    |
| AI/ML          | scikit-learn, XGBoost (TBD) |
| Visualization  | Recharts, Plotly, etc. (TBD)|

---

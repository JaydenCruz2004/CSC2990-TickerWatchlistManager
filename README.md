# 📈 Ticker Watchlist Manager  

## Overview  
The **Ticker Watchlist Manager** is an Android application built for **CSC2990 Assignment 2**. The app automatically reads SMS messages formatted with stock ticker symbols and manages a watchlist of tickers. Users can view their saved tickers and open related financial info pages on **SeekingAlpha** directly inside the app.  

This project focuses on:  
- **Fragments** & **Content Providers**  
- **Broadcast Receivers (SMS_RECEIVED)**  
- **WebView integration**  
- Optional **extra credit** features  

---

## ✨ Features  

### Part A – Core App  
- **MainActivity**  
  - Hosts two fragments in **Landscape Mode**:  
    - `TickerListFragment` – displays up to 6 ticker symbols  
    - `InfoWebFragment` – loads SeekingAlpha pages  

- **TickerListFragment**  
  - Starts with **3 default tickers**: `NEE`, `AAPL`, `DIS`  
  - Clicking a ticker opens its info page in the `InfoWebFragment`  
  - Max of **6 tickers** in the list  
    - If more are added, the 6th ticker is replaced (or round robin if extra credit enabled)  

- **InfoWebFragment**  
  - Default page: [https://seekingalpha.com](https://seekingalpha.com)  
  - Loads ticker info pages dynamically with URL concatenation  

---

### Part B – SMS Integration  
- **SMSReceiver (BroadcastReceiver)**  
  - Listens for SMS with `SMS_RECEIVED` action  
  - Valid SMS format:  
    ```
    Ticker:<<TICKER>>
    ```
    - Example: `Ticker:<<BAC>>`  
  - Handles cases:  
    - ✅ **Valid ticker** → add to list + open info page in WebView  
    - ❌ **Invalid ticker** → show Toast “Invalid Ticker”  
    - ❌ **Wrong format** → show Toast “No valid watchlist entry found”  

- **Validation Rules**  
  - Converts lowercase/mixed case to uppercase (e.g., `bac` → `BAC`)  
  - Only allows **letters** (no numbers/symbols)  
  - Rejects: `D-i_s`, `T567`, `123-sD`, etc.  

---

## 📱 Extra Credit (Optional Features)  
- **Portrait Mode Support (25 pts)**  
  - `MainActivity` shows only `TickerListFragment`  
  - Clicking a ticker opens `WebActivity` with `InfoWebFragment`  

- **SharedPreferences Persistence (10 pts)**  
  - Saves ticker list across app restarts and device reboots  

- **Round Robin Replacement (10 pts)**  
  - Replaces oldest ticker in circular order instead of always the 6th  

- **Custom Extra Feature (up to 10 pts)**  
  - *(Describe here if implemented — e.g., dark mode, swipe to delete, etc.)*  

---

## 🛠️ Technical Details  
- **Minimum API Level:** 33 (Android 13.0)  
- **Tech Used:**  
  - Java / Kotlin  
  - Android Studio  
  - Fragments, Activities, WebView  
  - BroadcastReceiver (SMS)  
  - SharedPreferences (optional)  

---

## 📂 Project Structure  
app/
├── java/
│ ├── com.example.tickerwatchlist/
│ │ ├── MainActivity.java
│ │ ├── TickerListFragment.java
│ │ ├── InfoWebFragment.java
│ │ ├── SmsReceiver.java
│ │ ├── WebActivity.java (extra credit)
│ │ └── utils/ (validation, helpers)
│
├── res/
│ ├── layout/
│ │ ├── activity_main.xml
│ │ ├── fragment_ticker_list.xml
│ │ ├── fragment_info_web.xml
│ │ ├── activity_web.xml (extra credit portrait)
│ │ └── activity_main_land.xml (landscape mode)
│
└── AndroidManifest.xml

---

## 🚀 How to Run  
1. Clone the repository:  
   ```bash
   git clone https://github.com/JaydenCruz2004/CSC2990-TickerWatchlistManager.git
2.Open in Android Studio

3.Set minimum SDK to API 33 (Android 13)

4.Run on emulator or physical device
  -Make sure the emulator supports SMS sending

using APIs:

---

# Stock Info Fetcher App

## Overview

This is a simple Android application that allows users to search for a stock symbol and retrieve real-time stock price information along with the company name. The app fetches data from two APIs: Alpha Vantage (for stock price) and Finnhub (for company profile).

### Features
- Input the stock symbol to get the current stock price and percentage change.
- Displays the company name alongside the stock details.
- Animated UI with an image transparency effect and a hidden soft keyboard when searching.

## Prerequisites

- Android Studio (latest version recommended)
- Basic understanding of Android development and Java
- Internet connection for fetching API data
- An Android device or emulator to run the app

## APIs Used

1. **Alpha Vantage API**: Provides real-time stock price information.
   - [Alpha Vantage API](https://www.alphavantage.co/)
   - You can get your free API key from Alpha Vantage.
   
2. **Finnhub API**: Provides company profile details.
   - [Finnhub API](https://finnhub.io/)
   - You can sign up for a free Finnhub account to get an API token.

## Setup and Installation

1. **Clone or download the project**:
    ```
    git clone <repository-url>
    ```

2. **Open the project in Android Studio**:
    - Launch Android Studio and click on `File > Open`.
    - Navigate to the project directory and select it.
    - Let Android Studio sync the project and install dependencies.

3. **Get API Keys**:
    - Register for API keys from [Alpha Vantage](https://www.alphavantage.co/support/#api-key) and [Finnhub](https://finnhub.io/register).
    - Replace the API keys in `MainActivity.java` with your own keys:
      ```java
      // Replace these API keys with your own
      urls[0] = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + stock_symbol + "&apikey=YOUR_ALPHA_VANTAGE_API_KEY";
      urls[1] = "https://finnhub.io/api/v1/stock/profile2?symbol=" + stock_symbol + "&token=YOUR_FINNHUB_API_KEY";
      ```

4. **Build the project**:
    - Click on `Build > Make Project` in Android Studio to build the project.

5. **Run the app**:
    - To run the app on an emulator or a connected Android device, click on the `Run` button in Android Studio, or use the `Shift + F10` shortcut.

## How to Use the App

1. Launch the app on your Android device or emulator.
2. Enter a valid stock symbol (e.g., `AAPL` for Apple, `GOOGL` for Alphabet) into the search field.
3. Press the "Search" button.
4. The app will fetch and display:
   - The current stock price
   - Percentage change in stock price
   - The company name associated with the stock symbol
5. If an invalid stock symbol is entered, the app will display an appropriate error message.

## Notes

- Ensure you have a stable internet connection while using the app, as it fetches data from external APIs.
- In case of any errors in fetching or displaying data, the app will show error messages using `Toast`.

## Screenshots

(Include screenshots or GIFs showing the app in action)

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

You can modify this README file based on your project-specific details, such as screenshots or any additional features you may implement.

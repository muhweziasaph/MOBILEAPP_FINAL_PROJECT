# SACCO Savings App (Treasurer use only)

A comprehensive Android application for managing SACCO (Savings and Credit Cooperative Organization) savings accounts, built with Kotlin and jetpack as the primary technology than primary XML based ui approach because  jetpack compose provides simpler ,more modern and more efficient way of building android user interfaces  

## Features currently as its still under development 

- **User Authentication**: Register and login functionality for SACCO treasurer
- **member registration and deleting **: treasurer can add or delete a member
- **Deposits**: treasurer can make deposits members savings accounts
- **Transaction History**: treasurer can View complete transaction history with details
- **Local Database**: Uses Room database for offline data storage

## Technology Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database
- **UI**: Material Design Components
- **Coroutines**: For asynchronous operations
- **LiveData/StateFlow**: For reactive data handling

## Project Structure

```
app/
├── src/main/
│   ├── java/com/sacco/savings/
│   │   ├── data/
│   │   │   ├── model/          # Data models (User, Account, Transaction)
│   │   │   ├── dao/            # Room DAOs
│   │   │   └── database/       # Room database setup
│   │   ├── repository/         # Repository layer
│   │   ├── ui/
│   │   │   ├── fragments/      # UI fragments
│   │   │   ├── viewmodel/      # ViewModels
│   │   │   └── adapter/         # RecyclerView adapters
│   │   └── SaccoApplication.kt # Application class
│   └── res/                    # Resources (layouts, strings, etc.)
```

## Setup Instructions

1. **Prerequisites**:
   - Android Studio (latest version recommended)
   - JDK 17 or higher
   - Android SDK (API 24 minimum)

2. **Build the Project**:
   ```bash
   ./gradlew build
   ```

3. **Run the App**:
   - Open the project in Android Studio
   - Connect an Android device or start an emulator
   - Click "Run" or press Shift+F10

## Usage

1. **Registration**: 
   - Click "Register" on the login screen
   - Fill in member details (Member ID, Name, Email, Phone, Password)
   - A default savings account will be created automatically

2. **Login**:
   - Enter your email and password
   - Access your dashboard

3. **Dashboard**:
   - View your account balance and details
   - Make deposits or withdrawals
   - View transaction history

4. **Transactions**:
   - All deposits and withdrawals are recorded
   - View complete transaction history with dates and reference numbers

## Database Schema

- **Users**: Member information
- **Accounts**: Savings accounts linked to users
- **Transactions**: All financial transactions

## Security Notes

**Important note**: This is a demo application. In production:
- Passwords should be hashed (e.g., using bcrypt)
- Implement proper authentication tokens
- Add network security configurations
- Encrypt sensitive data
- Implement proper session management

## License

This project is created for educational purposes.


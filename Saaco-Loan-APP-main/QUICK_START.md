# Quick Start Guide

## Building and Running the App

1. **Open in Android Studio**:
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to this directory and select it

2. **Sync Gradle**:
   - Android Studio should automatically sync Gradle
   - If not, click "Sync Now" when prompted

3. **Run the App**:
   - Connect an Android device (with USB debugging enabled) or start an emulator
   - Click the "Run" button (green play icon) or press `Shift + F10`
   - Select your device/emulator

## First Time Setup

1. **Register a New User**:
   - On the login screen, click "Register"
   - Fill in the registration form:
     - Member ID: Any unique identifier (e.g., "MEM001")
     - First Name: Your first name
     - Last Name: Your last name
     - Email: Your email address
     - Phone Number: Your phone number
     - Password: Choose a password
     - Confirm Password: Re-enter your password
   - Click "Register"
   - A default savings account will be created automatically

2. **Login**:
   - Enter your email and password
   - Click "Login"

3. **Start Using the App**:
   - View your account balance on the dashboard
   - Make deposits or withdrawals
   - View your transaction history

## Testing the App

### Test Scenarios:

1. **Registration**:
   - Register with a new email
   - Try registering with the same email (should show error)

2. **Login**:
   - Login with correct credentials
   - Try incorrect password (should show error)

3. **Deposits**:
   - Make a deposit of any amount
   - Check that balance updates
   - Verify transaction appears in history

4. **Withdrawals**:
   - Try withdrawing more than balance (should fail)
   - Withdraw a valid amount
   - Check that balance updates correctly

5. **Transaction History**:
   - View all transactions
   - Verify dates, amounts, and reference numbers

## Troubleshooting

### Build Errors:
- Ensure you have Android SDK installed
- Check that Gradle sync completed successfully
- Try "File > Invalidate Caches / Restart"

### Runtime Errors:
- Check that you're using API 24 or higher
- Ensure device/emulator is running Android 7.0 (API 24) or higher

### Database Issues:
- The app uses Room database which stores data locally
- Uninstalling the app will delete all data
- Data persists between app restarts

## Notes

- All data is stored locally on the device
- No internet connection required
- Passwords are stored in plain text (for demo purposes only)
- In production, implement proper password hashing and security measures


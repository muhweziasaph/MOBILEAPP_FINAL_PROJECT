# Room Database Implementation

The SACCO Savings app uses **Room Database** for local data storage. All account creation, login, and transaction data is stored locally on the device.

## Database Structure

### Tables

1. **users** - Stores user account information
   - id (Primary Key, Auto-generated)
   - memberId (Auto-generated on registration)
   - firstName
   - lastName
   - email (Unique identifier for login)
   - phoneNumber
   - password
   - createdAt

2. **accounts** - Stores savings accounts
   - id (Primary Key, Auto-generated)
   - userId (Foreign Key → users.id)
   - accountNumber (Auto-generated)
   - balance
   - accountType
   - createdAt

3. **transactions** - Stores all financial transactions
   - id (Primary Key, Auto-generated)
   - accountId (Foreign Key → accounts.id)
   - transactionType (DEPOSIT, WITHDRAWAL, INTEREST)
   - amount
   - description
   - transactionDate
   - referenceNumber (Auto-generated)

## How It Works

### Account Creation Flow:
1. User fills registration form (First Name, Last Name, Email, Phone, Password)
2. System checks if email already exists in Room database
3. If unique, creates new User record in Room database
4. Auto-generates Member ID (format: MEM + timestamp)
5. Auto-creates a default Savings Account for the user
6. User can now login

### Login Flow:
1. User enters email and password
2. System queries Room database for matching email and password
3. If found, user is authenticated and logged in
4. User's account data is loaded from Room database

### Data Persistence:
- All data is stored locally on the device
- Data persists between app sessions
- Data is deleted only when app is uninstalled
- No internet connection required

## Database Location

The Room database file is stored at:
```
/data/data/com.sacco.savings/databases/sacco_database
```

## Implementation Details

- **Database Name**: `sacco_database`
- **Version**: 1
- **Migration Strategy**: `fallbackToDestructiveMigration()` (for development)
- **Thread Safety**: Singleton pattern with synchronized initialization
- **Queries**: All database operations use Kotlin Coroutines for async operations

## Key Components

1. **SaccoDatabase**: Main Room database class
2. **UserDao**: Data Access Object for user operations
3. **AccountDao**: Data Access Object for account operations
4. **TransactionDao**: Data Access Object for transaction operations
5. **SaccoRepository**: Repository pattern layer that uses the DAOs
6. **ViewModels**: Use the repository to interact with the database

## Testing

To verify Room database is working:

1. **Create Account**: Register a new user - data is saved to Room
2. **Login**: Use the registered email/password - queries Room database
3. **View Data**: All account balances and transactions come from Room
4. **Persistence**: Close and reopen app - data should still be there

The database is fully functional and all operations (create, read, update) work through Room!


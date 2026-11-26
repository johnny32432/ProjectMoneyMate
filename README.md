💸 MoneyMate — Expense Tracker

MoneyMate is an Android app for tracking personal expenses.
Users can add purchases, choose a category, and see the total amount spent.

👥 Team
- Kursakpaev Arkhat — project setup, data layer, navigation | Room database (Entity, DAO, Database, Repository)
- Marat Nuray — Dashboard and Details screens
- Zhanibek Zholdaspek — Add Expense and History screens
 
- ✨ Main features
- Dashboard with total expenses and spending by category
- Add a new expense (amount, title, category, note)
- History of all expenses with a category filter
- Details screen for each expense, where it can be deleted
 
- 📱 Screens
- Dashboard
- Add Expense
- History
- Details
  
- 🛠 Tech stack
- Kotlin
- Jetpack Compose (Material 3)
- MVVM architecture (ViewModel + StateFlow)
- Navigation Compose
 
- 🚧 Status
<<<<<<< HEAD
  In development (Week 12)

## Exchange Rates (REST API)
- Data from the public API `https://open.er-api.com/v6/latest/KZT` via Retrofit + Gson
- Repository → ViewModel (StateFlow) → Compose UI
- Loading, Success, Error (with Retry) and Empty states
- Country flags are loaded with Coil
=======
  In development (Week 14)
>>>>>>> ac1336f3d2afb09903d57e3a585b91a61b4addc3

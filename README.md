💸 MoneyMate — Expense Tracker

MoneyMate is an Android app for tracking personal expenses.
Users can add purchases, choose a category, and see the total amount spent.

👥 Team
- Zhanibek Zholdaspek— project setup, data layer, navigation
- Marat Nuray — Dashboard and Details screens
- Kursakpaev Arkhat — Add Expense and History screens
 
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
  In development (Week 12)
- ## Settings & editing
- Dark/light theme preference is saved with DataStore and restored after restart
- Expenses can be edited (Update in Room) from the Details screen; the Add Expense form is reused for editing
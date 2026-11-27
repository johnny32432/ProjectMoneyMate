# 💸 MoneyMate — Expense Tracker

MoneyMate is an Android app for tracking personal expenses.
Users can add, edit and delete purchases, choose a category, see the total amount spent
and check current exchange rates. All data is saved on the device.

## 👥 Team
| Name | Week 12 | Week 13 |
|---|---|---|
| Kursakpaev Arkhat | Project setup, data layer, navigation | Room database (Entity, DAO, Database, Repository) |
| Marat Nuray | Dashboard and Details screens | Exchange rates screen (Retrofit, Coil) |
| Zhanibek Zholdaspek | Add Expense and History screens | Editing expenses (Update) and dark theme (DataStore) |

## ✨ Main features
- Dashboard with total expenses and a donut chart by category
- Add, edit and delete expenses (full CRUD)
- History of all expenses grouped by day with a category filter
- Details screen for each expense
- Exchange rates of popular currencies to tenge (KZT) loaded from the internet
- Dark / light theme
- All expenses and settings are kept after the app is closed and reopened

## 📱 Screens
- **Home (Dashboard)** — total, chart by category, recent expenses
- **Add / Edit Expense** — form with validation
- **History** — expenses by day, category filter
- **Details** — expense info, Edit and Delete buttons
- **Rates** — exchange rates with country flags
- **Settings** — dark theme switch

## 🌐 REST API (Retrofit)
- Public API: https://open.er-api.com (base currency KZT)
- `RatesApi` — Retrofit interface, JSON is converted to `RatesResponse` data class
- `RatesRepository` → `RatesViewModel` (coroutines + StateFlow) → `RatesScreen`
- UI states: **Loading**, **Success**, **Error** (with Retry button) and **Empty**
- Country flags are loaded with **Coil**

## 💾 Local storage
**Room**
- `ExpenseEntity` — table of expenses
- `ExpenseDao` — Create, Read, Update, Delete queries
- `AppDatabase` — Room database
- `ExpenseRepository` exposes data as `StateFlow`, so the UI updates automatically

**DataStore**
- Dark / light theme preference is saved with Preferences DataStore
- The theme is restored after restarting the app

## 🏗 Architecture (MVVM)
```
data/          Models, Room (local/), Retrofit (remote/), repositories
viewmodel/     ViewModels with StateFlow
ui/screens/    Compose screens
ui/components/ Reusable composables
navigation/    Routes, NavHost, BottomBar
```

## 🛠 Tech stack
- Kotlin, Coroutines, Flow / StateFlow
- Jetpack Compose (Material 3)
- MVVM (ViewModel)
- Navigation Compose
<<<<<<< HEAD
- Retrofit + Gson
- Coil
- Room
- DataStore

## 🚧 Status
Week 13 — REST API, Room and DataStore are implemented.
=======
 
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
>>>>>>> 2f650c5e3d444a4d2caf4bc769b91c18782998e5

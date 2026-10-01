# Kindness Points 💛

> ⚠️ **Personal project by Rambabu Dhanavath — work in progress.** A social-good gaming app starter; donation flows are simulated until NGO partnerships are finalized.

## 🌟 Vision

What if playing a game could feed an orphan, comfort an elder, or fund a child's education?

**Kindness Points** is a gamified social-good app for kids (and the young at heart). Players earn points for **good deeds and healthy activities** — helping an elderly person, exercising, watching educational videos — and those accumulated points are converted into **real-money donations** to verified orphanages, old-age homes, and social welfare causes.

Play games. Do good. Fund kindness.

## 🔄 How It Works

```
1. DO GOOD            2. EARN POINTS          3. POINTS POOL           4. REAL DONATIONS
┌─────────────┐      ┌──────────────┐      ┌──────────────────┐      ┌─────────────────────┐
│ Help an     │      │ 10 pts for   │      │ Points accrue    │      │ Points convert to   │
│ elderly     │─────▶│ helping a    │─────▶│ in the community │─────▶│ money donated to    │
│ person,     │      │ blind woman  │      │ pool / personal  │      │ verified NGOs:      │
│ exercise,   │      │ with her     │      │ wallet           │      │ orphanages,         │
│ learn       │      │ walking      │      │                  │      │ old-age homes,      │
└─────────────┘      │ stick        │      └──────────────────┘      │ welfare causes      │
                     └──────────────┘                               └─────────────────────┘
```

## ✨ Features

- 📝 **Deed logging with photo proof** — log a good deed, attach a photo, get it verified
- 👛 **Points wallet** — track earned, donated, and redeemed points
- 🏆 **Leaderboard** — friendly competition among friends, schools, and communities
- 🏠 **NGO directory** — browse verified orphanages, old-age homes, and welfare organizations
- 💸 **Donation tracking** — see exactly where converted donations go, with receipts
- 🎮 **Challenges & streaks** — daily kindness challenges and activity streaks for walking/exercise/learning
- 👪 **Parental controls** — safe, kid-friendly experience with guardian oversight

### Example point rewards

| Good deed | Points |
|---|---|
| Help an elderly person (e.g., assist a blind woman with her walking stick) | 10 |
| Walking / exercise milestone | 5 |
| Watch an educational video | 3 |
| Volunteer at a community drive | 15 |
| Daily kindness streak bonus | 2 |

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Mobile app | Android (Java) — *React Native planned as an alternative* |
| Backend | Spring Boot, REST APIs |
| Database | MySQL |
| Auth | JWT-based authentication |
| Storage | Photo proof uploads |

## 🏗️ System Architecture

```
┌──────────────┐     HTTPS/REST     ┌──────────────────┐     JDBC      ┌───────────┐
│  Android App │ ◄───────────────► │  Spring Boot     │ ◄───────────► │   MySQL   │
│  (mobile/)   │                   │  Backend         │               │           │
│              │                   │  (backend/)      │               │  users,   │
│ Home • Log   │                   │ UserController   │               │  deeds,   │
│ Deed • Wallet│                   │ DeedController   │               │  points,  │
│ Leaderboard  │                   │ PointsService    │               │  NGOs,    │
└──────────────┘                   │ DonationService  │               │ donations │
                                   └──────────────────┘               └───────────┘
```

## 📁 Project Structure

```
kindness-points/
├── mobile/                                   # Android app (Java)
│   └── app/src/main/java/com/rambabu/kindnesspoints/
│       ├── MainActivity.java                 # Home screen
│       ├── LogDeedActivity.java              # Log a deed + photo proof
│       ├── WalletActivity.java               # Points wallet
│       └── LeaderboardActivity.java          # Leaderboard
├── backend/                                  # Spring Boot backend
│   └── src/main/java/com/rambabu/kindnesspoints/
│       ├── UserController.java               # User registration & profiles
│       ├── DeedController.java               # Deed logging & verification
│       ├── PointsService.java                # Points earning & wallet logic
│       └── DonationService.java              # Points → donation conversion
│   └── src/main/resources/
│       └── schema.sql                        # MySQL database schema
└── README.md
```

## 🚀 Getting Started

### Backend

```bash
cd backend
# Configure MySQL credentials in src/main/resources/application.properties
# Create the schema:
mysql -u root -p kindness_points < src/main/resources/schema.sql
# Run:
mvn spring-boot:run
```

### Mobile app

1. Open `mobile/` in Android Studio.
2. Point the API base URL at your backend.
3. Build and run — log your first good deed! 💛

## 🗺️ Roadmap

- [ ] Deed verification workflow (community + admin review)
- [ ] NGO onboarding and verification portal
- [ ] Real payment/donation gateway integration
- [ ] Step-counter integration for automatic exercise points
- [ ] React Native cross-platform app
- [ ] Multi-language support
- [ ] Impact dashboard: total meals funded, elders helped, etc.

## 🤝 Contributing

Ideas and pull requests are welcome — especially around NGO verification, child safety, and donation transparency.

## 📄 License

MIT

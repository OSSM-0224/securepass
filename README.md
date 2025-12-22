# 🔐 SecurePass

A modern, secure, and lightweight **Android Password Manager** built with **Kotlin & Jetpack Compose**.  
SecurePass helps users safely store passwords with **encryption**, **biometric authentication**, and **real-time password strength validation** — all wrapped in a clean, intuitive UI.

🔗 **Repository:** https://github.com/OSSM-0224/securepass
---
## 🛠️ Tech Stack

| Tech | Usage |
|-----|------|
| 🟣 Kotlin | Programming language |
| 🎨 Jetpack Compose | UI toolkit |
| 🗄️ Room Database | Local storage |
| 🔐 Android Security Crypto | Encryption |
| 🧬 Biometric API | Fingerprint / Face auth |
| 🔁 Coroutines | Background tasks |
| 🧠 MVVM | Architecture pattern |

---
## 🚀 Features

- 🔒 Encrypted password storage (Android Security Crypto)
- 🧠 Real-time password strength meter (Weak → Medium → Strong)
- ✏️ Edit password validation with proper error handling
- 🔐 Biometric authentication support (Fingerprint / Face)
- ➕ Add password using modern Bottom Sheet UI
- 📋 Clean password list with reusable components
- 🎨 Fully built using Jetpack Compose (Material 3)
- ⚡ Smooth, responsive, and user-friendly UI

---

## 📁 Project Folder Structure
```bash
com/oysm/securepass/
├── data/
│   ├── local/
│   ├── models/
│   └── repository/
├── ui/
│   ├── screens/
│   ├── components/
│   └── theme/
├── viewmodel/
├── security/
└── (MainActivity.kt, App.kt in root)
```
---

# 🚀 Getting Started
## Step 1️⃣: Create Folder Structure
### How to create folders:
1. Right-click on com.oysm.securepass
2. Select New → Package
3. Create packages like:
   - data.local
   - data.models
   - data.repository
   - ui.screens
   - ui.components
   - ui.theme
   - viewmodel
   - security

## Step 2️⃣: Copy Files in Order
### 📦 Data Layer
- PasswordEntity.kt → data/models/
- PasswordDao.kt → data/local/
- PasswordDatabase.kt → data/local/
- PasswordRepository.kt → data/repository/

### 🔐 Security
- EncryptionUtil.kt → security/
- BiometricAuthManager.kt → security/ (optional)

### 🎨 UI Theme
- Color.kt → ui/theme/
- Typography.kt → ui/theme/
- Theme.kt → ui/theme/

### 📱 UI Screens
- HomeScreen.kt → ui/screens/
- AddPasswordBottomSheet.kt → ui/screens/
- EditPasswordScreen.kt → ui/screens/
- AuthenticationScreen.kt → ui/screens/

### ✏️ EditPassword Enhancements
- Secure password fields
- No auto-capitalization / auto-correct
- Validation for empty & mismatched passwords
- Real-time password strength meter
- Disabled buttons until valid input
- Clean spacing & layout

### 🧩 UI Components
- PasswordListItem.kt
- PasswordStrengthMeter.kt
- PasswordGeneratorDialog.kt

### PasswordStrengthMeter
```bash
<PasswordStrengthMeter password={newPassword} />
```
- Animated strength bar
- Color-coded indicators

### ⚙️ ViewModel & Core
- PasswordViewModel.kt → viewmodel/
- MainActivity.kt → root package
- App.kt → root package

## 🧾 Step 3️⃣: AndroidManifest Update
```bash
<uses-permission android:name="android.permission.USE_BIOMETRIC" />

<application
    android:name=".App"
    ... >
```
## 📦 Step 4️⃣: Gradle Dependencies
```bash
implementation("androidx.room:room-runtime:2.5.0")
kapt("androidx.room:room-compiler:2.5.0")
implementation("androidx.room:room-ktx:2.5.0")

implementation("androidx.security:security-crypto:1.1.0")
implementation("androidx.biometric:biometric:1.2.0")

implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.0")
```

### Enable KAPT:
```bash
id("org.jetbrains.kotlin.kapt")
```

## ▶️ Step 5️⃣: Build & Run
```bash
./gradlew clean build
./gradlew installDebug
```

- Or simply click Run ▶️ in Android Studio.

### ✅ Verification Checklist
- [x] App launches with lock screen
- [x] Biometric authentication works
- [x] Home screen empty state visible
- [x] Add password using + button
- [x] Password strength updates live
- [x] Password saved & listed
- [x] Edit validation works correctly

### 🚨 Common Errors & Fixes
| Error | Solution |
|------|-------|
| Unresolved reference | Check package name |
| Cannot resolve symbol | Clean & rebuild |
| Database error | Clear app data |
| Compose not found | Check BOM version |

### 📝 Package Name Reminder

#### All files must start with:
``` bash
package com.oysm.securepass.xxx
```


##### Example:

- data/models/PasswordEntity.kt
- ui/screens/HomeScreen.kt
---- 


## 📸 Screenshots


| Home | Password List |
|------|---------------|
| <img src="https://github.com/user-attachments/assets/2b0281a7-530a-4339-89c1-26c2039302fb" width="200" /> | <img src="https://github.com/user-attachments/assets/ee90d014-0a8e-4eae-8dd1-9adca8902653" width="200" /> |

| Add Password | Strength Meter |
|--------------|----------------|
| <img src="https://github.com/user-attachments/assets/f3b18504-1403-4b3f-83d9-fcca2e796a6c" width="200" /> | <img src="https://github.com/user-attachments/assets/84e9cd39-9e52-4381-b253-4cc29bb9ff3f" width="200" /> |

| Account Details | Show / Edit Password |
|-----------------|----------------------|
| <img src="https://github.com/user-attachments/assets/e6097464-566d-43fa-9e33-8dcf1bbb499e" width="200" /> | <img src="https://github.com/user-attachments/assets/491e2932-14cd-42f2-a977-4d23e674fa35" width="200" /> |



# ✍️ Author
Made with ❤️ by Om Mhatre
Feel free to fork, star ⭐, and contribute!

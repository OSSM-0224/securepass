# securepass

🔐 SecurePass

Repository: https://github.com/OSSM-0224/securepass

Description: A secure password manager Android app built with Kotlin & Jetpack Compose. Features include password storage, real-time password strength meter, EditPassword validation, and a clean, user-friendly UI.

⚡ Quick Setup Guide - Copy & Paste Files (Updated)
Step 1️⃣: Create Folders in Android Studio

In your Android Studio project, create these folder structure under src/main/java/com/oysm/securepass/:

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


How to create folders:

Right-click on com.oysm.securepass package

Select "New" → "Package"

Type folder name (e.g., data.local)

Repeat for all folders

Step 2️⃣: Copy Files in This Order
📁 DATA LAYER (Copy these files)

PasswordEntity.kt → data/models/

PasswordDao.kt → data/local/

PasswordDatabase.kt → data/local/

PasswordRepository.kt → data/repository/

🔐 SECURITY (Copy these files)

EncryptionUtil.kt → security/

BiometricAuthManager.kt → security/ (optional, can be ignored if biometric not used)

🎨 UI THEME (Copy these files)

Color.kt → ui/theme/

Typography.kt → ui/theme/

Theme.kt → ui/theme/

📱 UI SCREENS (Copy these files)

HomeScreen.kt → ui/screens/

AddPasswordBottomSheet.kt → ui/screens/ (renamed from AddPasswordScreen)

EditPasswordScreen.kt → ui/screens/

AuthenticationScreen.kt → ui/screens/

EditPasswordScreen Changes:

secureTextEntry={true} added for password fields

autoCapitalize="none" & autoCorrect={false}

Proper onChangeText usage

Validation: old password empty check, new & confirm match check

Real-time password strength meter integrated below new password field

Buttons disabled until password valid

Cleaner UI layout and spacing

🧩 UI COMPONENTS (Copy these files)

PasswordListItem.kt → ui/components/

PasswordStrengthMeter.kt → ui/components/

PasswordGeneratorDialog.kt → ui/components/

PasswordStrengthMeter Notes:

Accepts password prop: <PasswordStrengthMeter password={newPassword} />

Shows strength bar (Weak → Medium → Strong)

Optional smooth animation for bar

⚙️ VIEWMODEL & MAIN (Copy these files)

PasswordViewModel.kt → viewmodel/

MainActivity.kt → root com.oysm.securepass/

App.kt → root com.oysm.securepass/

Step 3️⃣: Update AndroidManifest.xml
<uses-permission android:name="android.permission.USE_BIOMETRIC" />


Update application tag:

<application
    android:name=".App"
    ...>

Step 4️⃣: Update build.gradle.kts
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    
    implementation("androidx.security:security-crypto:1.1.0")
    implementation("androidx.biometric:biometric:1.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.0")
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
}

Step 5️⃣: Build & Run
# Clean build
./gradlew clean build

# Run on device/emulator
./gradlew installDebug


Or click the "Run" button in Android Studio.

✅ Verification

After running, check:

 App launches with lock screen

 Tap "Authenticate" button works

 Home screen shows empty state

 + button visible at bottom right

 Can tap + to add password (via AddPasswordBottomSheet)

 Fill in account details

 Password strength meter shows real-time strength

 Save password and see it in list

 EditPassword screen: validation & error messages work

🚨 If You Get Errors
Error: "Unresolved reference"

→ Make sure package name in file matches folder structure

Error: "Cannot resolve symbol"

→ Clean build: ./gradlew clean build

Error: "Database error"

→ Delete app data and rebuild

Error: "Compose not found"

→ Check Compose BOM version in build.gradle.kts

📝 Package Name Reminder

All files start with:

package com.oysm.securepass.xxx


Where xxx is the folder path. Example:

data/models/PasswordEntity.kt → package com.oysm.securepass.data.models

ui/screens/HomeScreen.kt → package com.oysm.securepass.ui.screens

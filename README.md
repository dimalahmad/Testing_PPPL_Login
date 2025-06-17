# TestingPPPL - Automated Login Testing for SIMBAT

Selamat datang di proyek TestingPPPL! 🚀

Proyek ini adalah _automated testing suite_ untuk fitur login pada aplikasi SIMBAT menggunakan Java, Selenium WebDriver, dan Cucumber (BDD). Dengan pendekatan _Behavior Driven Development_, pengujian menjadi lebih mudah dipahami dan dikembangkan.

---

## ✨ Fitur Utama
- Pengujian otomatis untuk skenario login positif & negatif
- Menggunakan _Page Object Model_ untuk pemeliharaan kode yang lebih baik
- Laporan hasil pengujian otomatis (HTML)
- Contoh skenario BDD dengan Gherkin

---

## 🛠️ Teknologi & Library
- Java 17/23
- Maven
- Selenium WebDriver
- Cucumber (Gherkin)
- JUnit 5
- ExtentReports

---

## 📁 Struktur Proyek
```
TestingPPPL/
├── src/
│   ├── main/java/pages/         # (Page Object, kosong)
│   └── test/java/
│        ├── pages/             # Page Object untuk pengujian
│        ├── stepDefinitions/   # Step Definition Cucumber
│        └── runners/           # (Runner, jika ada)
│   └── test/resources/features/ # File .feature (Gherkin)
├── pom.xml                     # Konfigurasi Maven & dependencies
└── README.md                   # Dokumentasi proyek
```

---

## 🚀 Cara Menjalankan
1. Clone repo ini
2. Install dependencies
   ```bash
   mvn clean install
   ```
3. Jalankan pengujian
   ```bash
   mvn test
   ```
4. Lihat laporan hasil
   - Buka `target/cucumber-reports.html` di browser

---

## 📝 Contoh Skenario Pengujian (Gherkin)
```gherkin
Feature: SIMBAT Login Functionality
  As a user
  I want to be able to login to the SIMBAT system
  So that I can access the system's features

  @login @positive
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid email "admin@simbat.disyfa.site"
    And I enter valid password "admin"
    And I click the login button
    Then I should be logged in successfully

  @login @negative
  Scenario Outline: Failed login with invalid credentials
    Given I am on the login page
    When I enter email "<email>"
    And I enter password "<password>"
    And I click the login button
    Then I should remain on the login page
    And the login form should be empty

    Examples:
      | email                    | password           |
      | apalah123@gmail.com     | admin             |
      | admin@simbat.disyfa.site| linggangguliguli  |
      |                         | admin             |
      | admin@simbat.disyfa.site|                   |
      |                         |                   |
```

---

## 👨‍💻 Kontribusi
Kontribusi sangat terbuka! Silakan _fork_, buat _branch_, dan ajukan _pull request_.

---

## 📢 Lisensi
Proyek ini menggunakan lisensi MIT.

---

Happy Testing! 🎉

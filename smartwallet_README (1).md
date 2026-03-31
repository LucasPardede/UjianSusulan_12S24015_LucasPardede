# M02 Smart Wallet System (Advanced OOP Assignment)

Pada tugas ini, anda diminta untuk mengembangkan sistem **Smart Wallet
berbasis layanan (service-based wallet)** dengan kompleksitas lebih
tinggi dibandingkan sistem wallet biasa.

Sistem harus mendukung berbagai tipe akun, transaksi dengan fee,
cashback, serta subscription.

Setiap akun memiliki:
- nama pemilik
- username
- saldo
- tipe akun (BASIC / PREMIUM)

Akun dapat menerima berbagai jenis transaksi seperti:
- **deposit**
- **withdraw**
- **transfer**
- **subscription**

Setiap transaksi memiliki informasi:
- transaction id
- username
- amount
- fee
- netAmount
- waktu transaksi
- deskripsi

Untuk mendukung pengembangan sistem yang lebih fleksibel, transaksi
harus direpresentasikan menggunakan **konsep pewarisan (inheritance)**.

Semua kelas model harus ditempatkan pada package:

    smartwallet.model

Driver class harus ditempatkan pada package:

    smartwallet.driver

------------------------------------------------------------------------

# Task 01: Multi-Type Account (20 pts)

Driver yang dikembangkan:

    smartwallet.driver.Driver1

Setiap akun memiliki:
- name
- username
- balance
- accountType (BASIC / PREMIUM)

Format masukan:

    create-account#<name>#<username>#<type>

Program membaca masukan hingga:

    ---

Output:

    username|name|type|balance

Saldo awal:

    0.0

### Contoh Input

    create-account#Wiro Sableng#wirsab#BASIC
    create-account#Jaka Sembung#jaksem#PREMIUM
    ---

### Output

    wirsab|Wiro Sableng|BASIC|0.0
    jaksem|Jaka Sembung|PREMIUM|0.0

------------------------------------------------------------------------

# Task 02: Transaction with Fee (25 pts)

Tambahkan kelas abstrak:

    smartwallet.model.Transaction

Properti minimal:

    id
    username
    amount
    fee
    netAmount
    timestamp
    description

Subclass:

    DepositTransaction
    WithdrawTransaction

Aturan:

1.  Deposit: tanpa fee
2.  Withdraw: fee 5%
3.  Saldo menggunakan netAmount

Driver:

    smartwallet.driver.Driver2

Format input:

Deposit

    deposit#<username>#<amount>#<timestamp>#<description>

Withdraw

    withdraw#<username>#<amount>#<timestamp>#<description>

Withdraw tidak boleh menyebabkan saldo negatif.

### Contoh Input

    create-account#Jaka Sembung#jaksem#BASIC
    deposit#jaksem#100.0#2023/02/14 10:10:10#Salary
    withdraw#jaksem#30.0#2023/02/15 11:00:00#Food
    ---

### Output

    jaksem|Jaka Sembung|BASIC|71.5

------------------------------------------------------------------------

# Task 03: Transfer + Cashback (30 pts)

Subclass baru:

    TransferTransaction
    CashbackTransaction

Format input:

    transfer#<sender>#<receiver>#<amount>#<timestamp>#<description>

Aturan:

1.  Sender fee 2%
2.  Receiver mendapat full amount
3.  Akun PREMIUM → cashback 2%
4.  Cashback dicatat sebagai CashbackTransaction
5.  Saldo pengirim tidak boleh negatif

Driver:

    smartwallet.driver.Driver3

### Contoh Input

    create-account#Naruto Uzumaki#naruto#PREMIUM
    create-account#Sasuke Uchiha#sasuke#BASIC
    deposit#naruto#100.0#2023/02/14 10:10:10#Mission reward
    transfer#naruto#sasuke#40.0#2023/02/15 12:00:00#Team fund
    ---

### Output

    naruto|Naruto Uzumaki|PREMIUM|59.2
    sasuke|Sasuke Uchiha|BASIC|40.0

------------------------------------------------------------------------

# Task 04: Subscription (25 pts)

Driver:

    smartwallet.driver.Driver4

Subclass baru:

    SubscriptionTransaction

Format input:

    subscribe#<username>#<monthly_fee>#<timestamp>#<description>

Aturan:

1.  Jika saldo tidak cukup → InsufficientBalanceException
2.  Program **tidak boleh berhenti**

## Custom Exception

Buat class:

    InsufficientBalanceException

Exception dibangkitkan ketika saldo tidak mencukupi untuk subscription.

### Contoh Input

    create-account#Wiro Sableng#wirsab#BASIC
    deposit#wirsab#50.0#2023/02/14 10:10:10#Income
    subscribe#wirsab#20.0#2023/02/15 11:00:00#Netflix
    subscribe#wirsab#40.0#2023/02/16 09:00:00#Spotify
    ---

### Output

    wirsab|Wiro Sableng|BASIC|30.0

------------------------------------------------------------------------

# Task 05: Transaction History (BONUS)

Driver:

    smartwallet.driver.Driver5

Perintah tambahan:

    show-account#<username>

Format akun:

    username|name|type|balance

Format transaksi:

    id|type|amount|fee|netAmount|timestamp|description

Transaksi harus diurutkan berdasarkan **timestamp ascending**.

### Contoh Input

    create-account#Wiro Sableng#wirsab#BASIC
    deposit#wirsab#50.0#2023/02/14 10:10:10#Income
    withdraw#wirsab#20.0#2023/02/15 11:00:00#Food
    show-account#wirsab
    ---

### Output

    wirsab|Wiro Sableng|BASIC|31.0
    1|deposit|50.0|0.0|50.0|2023/02/14 10:10:10|Income
    2|withdraw|20.0|1.0|19.0|2023/02/15 11:00:00|Food

------------------------------------------------------------------------

# How to Compile and Run

Pastikan posisi berada pada direktori:

    bin

Compile:

    cd bin
    javac ..\src\smartwallet\driver\*.java ..\src\smartwallet\model\*.java -d .

Run:

    java smartwallet.driver.Driver5

------------------------------------------------------------------------

# Constraint

-   Wajib OOP (inheritance, polymorphism)
-   Dilarang IF-ELSE panjang
-   Transaction ID auto increment
-   Exception tidak boleh menghentikan program

------------------------------------------------------------------------

# Submission

    src/smartwallet/model/*.java
    src/smartwallet/driver/Driver1.java
    src/smartwallet/driver/Driver2.java
    src/smartwallet/driver/Driver3.java
    src/smartwallet/driver/Driver4.java
    src/smartwallet/driver/Driver5.java
    changelog.txt

submit ke github, kemudian kirim link ke ecourse
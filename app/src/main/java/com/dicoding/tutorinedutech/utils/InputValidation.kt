package com.dicoding.tutorinedutech.utils

fun CharSequence.validateEmail(): String? {
    val s = this
    val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    return if (!emailRegex.matches(s.toString())) "Masukkan format email yang sesuai." else null
}

fun CharSequence.validatePassword(): String? {
    val s = this
    val lengthRegex = Regex("^.{8,16}$")
    val digitRegex = Regex(".*[0-9].*")
    val lowercaseRegex = Regex(".*[a-z].*")
    val uppercaseRegex = Regex(".*[A-Z].*")
    val symbolRegex = Regex(".*\\W.*")
    val noSpaceRegex = Regex("^[^ ]*$")

    return when {
        !lengthRegex.matches(s) -> "Panjang 8-16 karakter."
        !digitRegex.matches(s) -> "Harus mengandung angka."
        !lowercaseRegex.matches(s) -> "Harus mengandung huruf kecil."
        !uppercaseRegex.matches(s) -> "Harus mengandung huruf besar."
        !symbolRegex.matches(s) -> "Harus mengandung simbol."
        !noSpaceRegex.matches(s) -> "Tidak boleh ada spasi."
        else -> null
    }
}

fun CharSequence.validateUsername(): String? {
    val s = this
    val lengthRegex = Regex("^[\\s\\S]{4,20}$")
    val usernameRegex = Regex("^(?=.{4,20}$)(?:[a-zA-Z\\d]+(?:(?:\\.|_)[a-zA-Z\\d])*)+$")
    return when {
        !lengthRegex.matches(s) -> "Panjang 4-20 karakter."
        !usernameRegex.matches(s) -> {
            when {
                !s.matches(Regex("^[a-zA-Z\\d]")) -> "Harus diawali huruf atau angka."
                s.contains(Regex("[^a-zA-Z\\d._]")) -> "Hanya boleh huruf, angka, titik, garis bawah."
                s.contains(Regex("([._]{2,})|([._]$)")) -> "Titik/garis bawah harus diikuti huruf/angka."
                else -> "Format username tidak valid."
            }
        }

        else -> null
    }
}

fun CharSequence.validatePhoneNumber(): String? {
    val phoneRegex = Regex("^\\+?[1-9]\\d{1,14}$")
    return if (!phoneRegex.matches(this)) {
        "Nomor telepon tidak valid."
    } else {
        null
    }
}

fun CharSequence.validateMaxLength(maxLength: Int): String? {
    return if (this.length > maxLength) {
        "Maksimal karakter $maxLength"
    } else if (this.isBlank()) {
        "Karakter tidak boleh kosong"
    } else {
        null
    }
}
package assesment1.currenSee.data

import assesment1.currenSee.R

object DataProvider {

    val currencies =
        Currencies(
            id = 1,
            nama = "Singapore / SGD",
            harga = "1,000 SGD = 11.800 IDR",
            negaraImageId = R.drawable.singapur
        )

    val currenciesList = listOf(
        currencies,
        Currencies(
            id = 2,
            nama = "Thailand / THB",
            harga = "1,000 THB = 433,0 IDR",
            negaraImageId = R.drawable.thailand
        ),
        Currencies(
            id = 3,
            nama = "China / CNY",
            harga = "1,000 CNY = 2.197 IDR",
            negaraImageId = R.drawable.china
        ),Currencies(
            id = 4,
            nama = "Malaysia / MYR",
            harga = "1,000 MYR = 3.353 IDR",
            negaraImageId = R.drawable.malaysia
        ),Currencies(
            id = 5,
            nama = "Australia / AUD",
            harga = "1,000 AUD = 10.510 IDR",
            negaraImageId = R.drawable.australia
        ),Currencies(
            id = 6,
            nama = "Jepang / JPY",
            harga = "1,000 JPY = 104,8 IDR",
            negaraImageId = R.drawable.japan
        ),Currencies(
            id = 7,
            nama = "Korea Selatan / KRW",
            harga = "1,000 KRW = 11,81 IDR",
            negaraImageId = R.drawable.korea
        ),Currencies(
            id = 8,
            nama = "Vietnam / VND",
            harga = "1,000 VND = 0,6371 IDR",
            negaraImageId = R.drawable.vietnam
        ),Currencies(
            id = 9,
            nama = "Turkey / TRY",
            harga = "1,000 TRY = 498,7 IDR",
            negaraImageId = R.drawable.turkey
        ),Currencies(
            id = 10,
            nama = "Rusia / RUB",
            harga = "1,000 RUB = 172,2 IDR",
            negaraImageId = R.drawable.rusia
        ),Currencies(
            id = 11,
            nama = "German / EUR",
            harga = "1,000 EUR = 17.270 IDR",
            negaraImageId = R.drawable.german
        )
    )
}
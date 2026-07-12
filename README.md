# CurrX — Fast & Simple Currency Converter

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android)
![Kotlin](https://img.shields.io/badge/Kotlin-2.1+-7F52FF?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?logo=jetpackcompose)
![API](https://img.shields.io/badge/API-Frankfurter%20%2B%20DolarAPI-000000)

CurrX is a dual-API currency converter for Android built with **Jetpack Compose**. It provides real exchange rates for LATAM countries via [DolarAPI.com](https://dolarapi.com) and interbank rates via [Frankfurter API](https://www.frankfurter.dev), giving you both market and official rates side by side.

---

## Features

- **Dual-rate display** — Compare LATAM market rates with interbank (ECB) rates
- **18+ currencies** — All Frankfurter-supported currencies plus 8 LATAM currencies
- **LATAM coverage** — ARS, BOB, BRL, CLP, COP, MXN, UYU, VES (BCV official rate)
- **Rate selector** — FilterChip UI to choose between available rate sources
- **Swap currencies** — One-tap from/to inversion
- **Material 3** — Modern UI with dynamic theming

## Screenshots

<!-- Add screenshots here once available -->

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin 2.1+ |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM (ViewModel + StateFlow) |
| HTTP | OkHttp + Retrofit |
| Serialization | kotlinx.serialization |
| Navigation | ModalBottomSheet |

## Architecture

```
MainActivity            — Composable UI (Container/Presentational)
 └─ MainViewModel       — State management via StateFlow<MainUiState>
     └─ ExchangeRateRepository — Orchestrates API calls & rate selection
         └─ ExchangeRateApi    — Retrofit interface (dolarapi + Frankfurter)
             └─ CountryInterceptor — Routes X-Country headers to dolarapi subdomains
```

### Data flow

1. User selects `from` and `to` currencies
2. Repository checks if either currency is in the LATAM map
3. If yes: fetches LATAM rates from DolarAPI **plus** interbank rate from Frankfurter
4. If no: fetches only the interbank rate
5. ViewModel exposes both as `List<RateOption>`; UI renders a `FilterChip` per option
6. User picks which rate to use, conversion updates in real time

## APIs

### DolarAPI.com
- Provides **market rates** (compra/venta) for LATAM countries
- Each country is hosted on a subdomain (e.g., `cl.dolarapi.com`, `ve.dolarapi.com`)
- Support tiers: free, attribution required (MIT license)
- Documentation: [dolarapi.com/docs](https://dolarapi.com/docs)

### Frankfurter API
- Provides **European Central Bank reference rates** (interbank)
- Covers 30+ world currencies
- Free, no API key required
- Documentation: [frankfurter.dev](https://www.frankfurter.dev)

## License

```
MIT License

Copyright (c) 2026 jsayago77

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

### Attribution

- **DolarAPI.com** — Rates provided under the [MIT license](https://dolarapi.com). See their site for full terms.
- **Frankfurter API** — Exchange rates provided by the European Central Bank via [frankfurter.dev](https://www.frankfurter.dev).

---

<div align="center">
  <sub>Built with ❤️ for LATAM</sub>
</div>

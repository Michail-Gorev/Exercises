<script>
import axios from 'axios';

export default {
    data() {
        return {
            amount: 0,  // цена товара
            baseCurrency: "USD",  // исходная валюта
            targetCurrencies: ["EUR", "GBP", "JPY", "RUB"],  // целевые валюты
            rates: {},  // курсы валют
            error: "",  // ошибка
            apiKey: "6edcb855a2e80de06695b0bbf8640c82"
        };
    },
    computed: {
        convertedPrices() {
            if (!this.rates || this.amount <= 0) return [];
            return this.targetCurrencies.map(currency => ({
                currency,
                amount: (this.amount * this.rates[currency]).toFixed(2)
            }));
        }
    },
    methods: {
        fetchRates() {
            this.error = "";
            const apiUrl = `https://api.exchangeratesapi.io/v1/latest?access_key=${this.apiKey}&base=${this.baseCurrency}`;

            axios.get(apiUrl)
                .then(response => {
                    if (response.data && response.data.rates) {
                        this.rates = response.data.rates;
                    } else {
                        this.error = "Не удалось загрузить курсы валют.";
                    }
                })
                .catch(error => {
                    this.error = "Ошибка получения данных. Проверьте подключение и API ключ.";
                    console.error("API Error:", error);
                });
        }
    },
    watch: {
        baseCurrency() {
            this.fetchRates();
        },
        amount() {
            if (this.amount < 0) {
                this.error = "Сумма должна быть положительным числом.";
            } else {
                this.error = "";
            }
        }
    },
    mounted() {
        this.fetchRates();
    }
};
</script>

<template>
    <div class="wrapper">
        <h1>Конвертер валют</h1>

        <label>Введите цену товара:</label>
        <input type="number" v-model.number="amount" min="0" placeholder="Введите сумму">

        <label>Выберите валюту:</label>
        <select v-model="baseCurrency">
            <option value="EUR">EUR</option>
        </select>

        <p class="error" v-if="error">{{ error }}</p>

        <div v-if="convertedPrices.length">
            <h2>Цена в других валютах:</h2>
            <ul>
                <li v-for="price in convertedPrices" :key="price.currency">
                    {{ price.currency }}: {{ price.amount }}
                </li>
            </ul>
        </div>
    </div>
</template>

<style scoped>
.wrapper {
    width: 400px;
    margin: auto;
    padding: 20px;
    background: #f5f5f5;
    border-radius: 8px;
    text-align: center;
}

.error {
    color: red;
}

.wrapper input, .wrapper select {
    margin-top: 10px;
    padding: 8px;
    font-size: 16px;
}
</style>

package com.sep.quiz.data.repository

import com.sep.quiz.data.remote.crypto.KucoinApiService
import com.sep.quiz.domain.DEFAULT_ERROR
import com.sep.quiz.domain.entiry.crypto.CandleEntity
import com.sep.quiz.domain.entiry.crypto.CurrencyEntity
import com.sep.quiz.domain.entiry.crypto.SingleTickerEntity
import com.sep.quiz.domain.entiry.crypto.TickerEntity
import com.sep.quiz.domain.repository.KucoinRepository
import com.sep.quiz.utils.ResultState
import javax.inject.Inject

class KucoinRepositoryImpl @Inject constructor(
    private val kucoinApiService: KucoinApiService
) : KucoinRepository {


    override suspend fun fetchCurrencyList(): ResultState<List<CurrencyEntity>> {
        val result = kucoinApiService.fetchCurrencyList()
        if (result.code == "200000") {
            result.let { responses ->
                return ResultState.Success(responses.data.map { it.toDomainModel() })
            }
        }
        return ResultState.Failure(DEFAULT_ERROR)
    }

    override suspend fun fetchTicker(symbol: String): ResultState<SingleTickerEntity> {
        val result = kucoinApiService.fetchTicker(symbol)
        if (result.code == "200000") {
            result.let { responses ->
                return ResultState.Success(responses.data.toDomainModel())
            }
        }
        return ResultState.Failure(DEFAULT_ERROR)
    }

    override suspend fun fetchAllTickers(): ResultState<List<TickerEntity>> {
        val result = kucoinApiService.fetchAllTickers()
        if (result.code == "200000") {
            result.let { responses ->
                return ResultState.Success(responses.data.ticker.map { it.toDomainModel() })
            }
        }
        return ResultState.Failure(DEFAULT_ERROR)
    }

    override suspend fun fetchMarketList(): ResultState<List<String>> {
        val result = kucoinApiService.fetchMarketList()
        if (result.code == "200000") {
            result.let { responses ->
                return ResultState.Success(responses.data)
            }
        }
        return ResultState.Failure(DEFAULT_ERROR)
    }

    override suspend fun fetchCandles(
        interval: String,
        symbol: String
    ): ResultState<List<CandleEntity>> {
        val result = kucoinApiService.fetchCandles(type = interval, symbol = symbol)
        if (result.code == "200000") {
            result.let { responses ->
                return ResultState.Success(responses.data.map {
                    CandleEntity(
                        time = it[0],
                        opening = it[1],
                        closing = it[2],
                        highest = it[3],
                        lowest = it[4],
                        volume = it[5],
                        amount = it[6]
                    )
                })
            }
        }
        return ResultState.Failure(DEFAULT_ERROR)
    }

    override suspend fun fetchServerTime(): ResultState<Long> {
        val result = kucoinApiService.serverTime()
        if (result.code == "200000") {
            result.let { responses ->
                return ResultState.Success(responses.data)
            }
        }
        return ResultState.Failure(DEFAULT_ERROR)
    }

    override suspend fun fetchPrices(): ResultState<String> {
        val result = kucoinApiService.getPrices()
        if (result.code == "200000") {
            result.let { responses ->
                return ResultState.Success(responses.data.BTC)
            }
        }
        return ResultState.Failure(DEFAULT_ERROR)
    }
}
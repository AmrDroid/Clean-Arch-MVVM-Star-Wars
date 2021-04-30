package com.amrmustafa.casestudy.di

import com.amrmustafa.casestudy.data.remote.repository.DetailsRepository
import com.amrmustafa.casestudy.data.remote.repository.SearchRepository
import com.amrmustafa.casestudy.domain.repository.IDetailsRepository
import com.amrmustafa.casestudy.domain.repository.ISearchRepository
import org.koin.dsl.module


val remoteDataSourceModule = module {

    single<ISearchRepository> { SearchRepository(apiService = get()) }

    single<IDetailsRepository> { DetailsRepository(apiService = get()) }

}
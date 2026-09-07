package org.ucb.appp1.di

import org.koin.core.module.Module

fun sharedModule(): List<Module> = listOf(
    dataModule,
    presentationModule,
    domainModule
)

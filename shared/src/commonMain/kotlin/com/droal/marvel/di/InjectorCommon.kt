package com.droal.marvel.di

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    var context: ContextArg? = null

    fun provideContextArgs(contextArg: ContextArg): ContextArg? {
        context = contextArg
        return context
    }


}

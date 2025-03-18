package com.jeong.sesac.chaksaicompose.common

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache

// 메모리 사이즈 = 0.15 ~ 0.2
const val COIL_MEMORY_CACHE_SIZE_PERCENT = 0.2

//Coil Disk Cache Size Setting
const val COIL_DISK_CACHE_DIR_NAME = "coil_file_cache"
const val COIL_DISK_CACHE_MAX_SIZE = 1024 * 1024 * 30


class ChakSaiApplication : Application(), SingletonImageLoader.Factory {
    override fun onCreate() {
        super.onCreate()
        composeApplication = this
    }

    companion object {
        private lateinit var composeApplication: ChakSaiApplication
        fun getInstance() = composeApplication
    }

    // coil image loader
    override fun newImageLoader(context: PlatformContext): ImageLoader = ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, COIL_MEMORY_CACHE_SIZE_PERCENT)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(filesDir.resolve(COIL_DISK_CACHE_DIR_NAME))
                .maximumMaxSizeBytes(COIL_DISK_CACHE_MAX_SIZE.toLong())
                .build()
        }.build()
}
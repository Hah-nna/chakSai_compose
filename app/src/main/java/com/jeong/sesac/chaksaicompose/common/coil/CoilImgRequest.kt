package com.jeong.sesac.chaksaicompose.common.coil

import android.util.Log
import coil3.compose.AsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.size.Size
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.ChakSaiApplication

const val COIL_CALL_TAG = "COIL_IMG"

class CoilImgRequest {
    companion object {
        fun getImgRequest(sourceImg: String) =
            ImageRequest.Builder(ChakSaiApplication.getInstance())
                .data(data = sourceImg)
                .size(Size.ORIGINAL)
                .error(R.drawable.err_img)
                .crossfade(true)
                .build()

        fun coilCallbackLog(imageSource: String, coilState: AsyncImagePainter.State) {
            when (coilState) {
                is AsyncImagePainter.State.Success -> {
                    Log.e(COIL_CALL_TAG, " $imageSource, Success")
                }
                is AsyncImagePainter.State.Error -> {
                    Log.e(COIL_CALL_TAG, "$imageSource, ${coilState.result.throwable.message}")
                }

                is AsyncImagePainter.State.Empty -> {
                    Log.e(COIL_CALL_TAG, "$imageSource, $coilState")
                }

                is AsyncImagePainter.State.Loading -> {
                    Log.e(COIL_CALL_TAG, "$imageSource, $coilState")
                }

                else -> {
                    Log.e(COIL_CALL_TAG, "$imageSource, Coil Error : $coilState")
                }
            }
        }
    }
}

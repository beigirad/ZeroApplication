package ir.beigirad.zeroapplication.network

import ir.beigirad.app.R
import ir.beigirad.zeroapplication.ZeroApplication
import ir.beigirad.zeroapplication.network.model.APIError
import ir.beigirad.zeroapplication.network.model.APIFailError
import retrofit2.Response
import java.io.IOException

/**
 * Created by farhad-mbp on 9/11/17.
 */

object ErrorUtils {

    fun parseError(response: Response<*>): APIError {
        val converter = APIProvider().getRetrofit()
                .responseBodyConverter<APIError>(APIError::class.java, arrayOfNulls(0))

        val error: APIError
        val errorText = StringBuilder()

        try {
            error = converter.convert(response.errorBody())
        } catch (e: IOException) {

            errorText.append(ZeroApplication.getAppContext().getString(R.string.error) + ":" + response.code() + '\n')
            when (response.code()) {
                401 -> errorText.append(ZeroApplication.getAppContext().getString(R.string.unathorized_error))
                404 -> errorText.append(ZeroApplication.getAppContext().getString(R.string.not_found_result))
                else -> errorText.append(ZeroApplication.getAppContext().getString(R.string.unknown_response_error))
            }


            return APIError(errorText.toString())
        }

        return error
    }

    fun parseFailure(throwable: Throwable?): APIFailError {
        var error: APIFailError? = null
        throwable?.message?.let {
            error = if (throwable.message!!.contains("Failed to connect"))
                APIFailError(ZeroApplication.getAppContext().getString(R.string.connecting_error), "")
            else if (throwable.message!!.contains("unexpected end of stream on"))
                APIFailError(ZeroApplication.getAppContext().getString(R.string.address_error), "")
            else
                APIFailError(ZeroApplication.getAppContext().getString(R.string.error) + ":" + throwable.message, "")
        }
        return error!!
    }


}
package com.biopassport.dphr.api

import com.biopassport.dphr.api.dto.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

class MarketplaceApi {
    private var accessToken = ""

    fun register(req: SignUpReq) {
        exchange("$API_URL/sign-up", HttpMethod.POST, Object::class.java, req = req)
    }

    fun auth(req: AuthReq) {
        val authRes = exchange("$API_URL/auth", HttpMethod.POST, AuthRes::class.java, req = req)

        if (!authRes.success) {
            throw IllegalStateException(AUTH_FAILED)
        }

        this.accessToken = authRes.accessToken
    }

    fun refreshAccessToken(req: RefreshAccessTokenReq): RefreshAccessTokenRes {
        return exchange("$API_URL/refresh-access-token", HttpMethod.POST, RefreshAccessTokenRes::class.java, req = req)
    }

    fun listCidrs(): CidrsRes {
        return exchange("$API_URL/cidrs", HttpMethod.GET, IpsRes::class.java, accessToken)
    }

    fun addCidr(req: CidrInfo) {
        exchange("$API_URL/cidr", HttpMethod.POST, Object::class.java, accessToken, req)
    }

    fun deleteCidr(req: CidrInfo) {
        exchange("$API_URL/cidr", HttpMethod.DELETE, Object::class.java, accessToken, req)
    }

    fun listProducts(): ProductsRes {
        return exchange("$API_URL/products", HttpMethod.GET, ProductsRes::class.java, accessToken)
    }

    fun listSubscriptions(): SubscribedRes {
        return exchange("$API_URL/subscribe-product", HttpMethod.GET, SubscribedRes::class.java, accessToken)
    }

    fun subscribeProduct(req: SubscribeReq): SubscribeRes {
        return exchange("$API_URL/subscribe-product", HttpMethod.POST, SubscribeRes::class.java, accessToken)
    }

    fun orderProduct(req: OrderReq): OrderRes {
        return exchange(
            "$API_URL/order-product",
            HttpMethod.POST,
            OrderRes::class.java,
            accessToken,
            req
        )
    }

    private fun <T> exchange(
        url: String,
        method: HttpMethod,
        responseType: Class<T>,
        accessToken: String? = null,
        req: Any? = null
    ): T {
        val headers = if (accessToken == null) getHeaders()
        else getAuthorizationHeaders(accessToken)
        val request = if (req == null) HttpEntity<String>(headers)
        else HttpEntity<String>(objectMapper.writeValueAsString(req), headers)

        return restTemplate.exchange(url, method, request, responseType).body ?: throw RestClientException("응답이 없습니다.")
    }

    private fun getHeaders(): HttpHeaders {
        return HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            add("cache-control", "no-cache")
        }
    }

    private fun getAuthorizationHeaders(accessToken: String): HttpHeaders {
        return HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            add("Authorization", "Basic $accessToken")
            add("cache-control", "no-cache")
        }
    }

    companion object {
        val restTemplate = RestTemplate()
        val objectMapper = ObjectMapper()

        const val API_URL = "https://*****.biopassport.io/rest/1"
        const val AUTH_FAILED = "인증에 실패했습니다. id, api key, password를 확인하십시오."
    }
}

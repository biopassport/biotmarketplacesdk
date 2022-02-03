package com.biopassport.dphr.example

import com.biopassport.dphr.api.MarketplaceApi
import com.biopassport.dphr.api.dto.AuthReq
import com.biopassport.dphr.api.dto.IpInfo
import com.biopassport.dphr.api.dto.OrderReq
import com.biopassport.dphr.api.dto.SignUpReq

fun main() {
    val marketplaceApi = MarketplaceApi()

    // 가입 신청
    val signUpReq = SignUpReq(
        "test@email.com",
        "tester",
        "010-0000-0000",
        "test corp.",
        "Data mining",
        "seoul",
        "seoul",
        "seoul gangnam",
        "000000",
        "000-00-0000"
    )
    marketplaceApi.register(signUpReq)
    // 가입신청 완료 후. 저희쪽 담당자가 심사 후 연락을 드립니다.

    // 가입신청 완료 후 사전에 받은 ID, API Key와 비밀번호를 이용해서 인증을 진행합니다.
    val authReq = AuthReq("test corp id", "test api key string", "test password")
    marketplaceApi.auth(authReq)

    // 인증을 완료한 후 등록된 CIDR 목록을 조회합니다.
    val listCidrs = marketplaceApi.listCidrs()
    // 등록된 CIDR 목록이 반환됩니다.
    println(listCidrs)

    // 인증을 완료한 후 CIDR 목록을 등록/ 삭제합니다.
    val cidrInfo = CidrInfo("0.0.0.0/0")
    marketplaceApi.addCidr(cidrInfo)
    marketplaceApi.deleteCidr(cidrInfo)

    // 인증을 완료한 후 상품 목록 조회
    val listProducts = marketplaceApi.listProducts()
    // 상품 목록이 반환됩니다.
    println(listProducts)

    // 인증을 완료한 후 상품 주문
    val orderReq = OrderReq("product-codde-A")
    val orderProduct = marketplaceApi.orderProduct(orderReq)
    // 가격과 입금 가상계좌 주소가 반환됩니다.
    println(orderProduct)
    // 주문이 완료되면 저희쪽 담당자가 확인 후 연락을 드립니다.
}

/*
 * Copyright 2020 BIONES PTE. LTD.
 *
 * BIONES PTE. LTD. licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.biopassport.dphr.api.dto

/** 구독중인 상품 조회 응답 */
data class SubscribedRes(
    val subscriptions: List<SubscribedInfo>
)

/** 구독중인 상품 정보 */
data class SubscribedInfo(
    /** 상품 코드 **/
    val code: String,
    /** 구독 일시 **/
    val subscribedAt: OffsetDateTime,
    /** 구독 횟수 **/
    val subscriptionCount: Int
)

/** 상품 구독 요청 **/
data class SubscribeReq(
    val code: String
)

/** 상품 구독 응답 **/
data class SubscribeRes(
    /** 상품 가격 */
    val price: BigDecimal,
    /** 통화 **/
    val currency: String,
    /** 입금 가상계좌 주소 */
    val virtualWalletAddress: String
)
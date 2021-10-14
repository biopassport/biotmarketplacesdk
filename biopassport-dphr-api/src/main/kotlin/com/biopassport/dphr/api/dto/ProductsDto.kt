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

import java.math.BigDecimal

/** 전체 상품 목록 */
data class ProductsRes(
    val products: List<Product>
)

data class Product(
    /** 상품 코드 */
    val code: String,
    /** 상품 명 */
    val name: String,
    /** 상품 설명 */
    val description: String,
    /** 상품 가격 */
    val price: BigDecimal,
    /** 통화 */
    val currency: String
)
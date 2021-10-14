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

/** 가입 신청 */
data class SignUpReq(
    /** 담당자 이메일 */
    val email: String,
    /** 담당자 이름 */
    val name: String,
    /** 담당자 연락처 */
    val phone: String,
    /** 회사 명 */
    val corpName: String,
    /** 회사 업종 */
    val corpType: String,

    /** 회사 도 */
    val corpState: String,
    /** 회사 도시 **/
    val corpCity: String,
    /** 회사 상세 주소 **/
    val corpAddress: String,
    /** 우편번호 **/
    val corpPostcode: String,

    /** 사업자 등록 번호 */
    val corpRegistNumber: String
)

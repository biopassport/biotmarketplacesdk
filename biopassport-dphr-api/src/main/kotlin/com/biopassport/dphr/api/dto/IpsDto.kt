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

/** 아이피 수정, 삭제, 등록 */
data class IpInfo(
    val ip: String
)

/** 아이피 리스트 **/
data class IpsRes(
    val ips: List<String>
)
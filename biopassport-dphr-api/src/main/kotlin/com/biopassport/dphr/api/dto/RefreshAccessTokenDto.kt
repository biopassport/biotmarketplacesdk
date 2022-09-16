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

/** AccessToken 갱신 요청 */
data class RefreshAccessTokenReq(
    val apiKey: String,
    val accessToken: String,
    val refreshToken: String
)

data class RefreshAccessTokenRes(
    /** access token */
    val accessToken: String
)
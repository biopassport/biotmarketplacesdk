# BIOT DPHR Marketplace SDK

![kotlin](https://img.shields.io/badge/kotlin-1.5.31-orange)
![spring-boot](https://img.shields.io/badge/spring--boot-2.5.5-brightgreen)

## Index

1. [소개](#1-소개)
2. [버전](#2-버전)
3. [인증](#3-인증)
4. [URI 구조](#4-URI-구조)
5. [요청 형식](#5-요청-형식)
6. [APIs](#6-APIs)
7. [상품 종류](#7-상품-종류)
8. [오류](#8-오류)
9. [라이선스](#9-라이선스)

---

## 1. 소개

BioPassport Marketplace는 추가로 Marketplace라는 구매자 측면의 인터페이스와 판매자(또는 사용자) 측면의 인터페이스로 구성됩니다. 모바일 애플리케이션 사용자는 Marketplace에
DPHR과 그에 해당하는 데이터를 등록할 수 있습니다. 잠재적 구매자는 데이터를 등록할 때 판매자에게 데이터 접근 및 사용 승인을 요청할 수 있습니다. 이후 사용자는 접근을 승인하거나 거부할 수 있습니다.

## 2. 버전

- version 0.9 Beta
- version 0.91 Beta
  - API 문서 수정
- version 0.95 Beta
  - API ACL 방식 IP -> CIDR로 변경

## 3. 인증

DPHR Marketplace API는 HTTP [기본 인증](https://en.wikipedia.org/wiki/Basic_access_authentication/) 을 사용합니다.

가입된 회사들이 사전에 받은 ID, API Key와 비밀번호를 이용해서 인증 키 값을 받습니다.

## 4. URI 구조

```
https://marketplace.biopassport.io/rest/1
```

URI 리소스를 표현할 때, 호스트 이름을 포함하지 않습니다.

    example

    [X] https://marketplace.biopassport.io/rest/2

    [O] /rest/2

## 5. 요청 형식

모든 요청과 응답의 표현은 JSON 형식입니다. 그리고 내용 형식(content type)은 ```application/json```과 UTF-8 문자를을 사용합니다.

DPHR Marketplace API는 표준 HTTP 메서드를 사용합니다.

인증을 통해 받은 access-token은 Http Authentication Header의 Bearer로 사용됩니다.

## 6. APIs

### 가입 신청 
신청 완료 후. 저희쪽 담당자가 심사 후 연락을 드립니다.

#### Request

`[Post] /rest/1/sign-up`

##### Field
```
{
  "email": string,
  "name": string,
  "phone": string,
  "corpName": string,
  "corpType": string,
  "corpState": string,
  "corpCity": string,
  "corpAddress": string,
  "corpPostcode": string,
  "corpRegistNumber": string
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| email | 담당자 이메일 | String |
| name | 담당자 이름 | String |
| phone | 담당자 연락처 | String |
| corpName | 회사 명 | String |
| corpType | 회사 업종 | String |
| corpState | 회사 도 | String |
| corpCity | 회사 도시 | String |
| corpAddress | 회사 상세 주소 | String |
| corpPostcode | 회사 우편번호| String |
| corpRegistNumber | 사업자 등록번호 | String |

### 인증 
가입신청 완료 후 사전에 받은 ID, API Key와 비밀번호를 이용해서 인증을 진행합니다. 

#### Request
`[Post] /rest/1/auth`

##### Header
```
Authorization: Bearer access-token
```

| 헤더 | 설명 | 타입 |
|----|------------|-----|
| Authorization | 인증 정보 | String |

##### Field
```
{
  "id": string,
  "apiKey": string,
  "password": string
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| id | 아이디 | String |
| apiKey | api key | String |
| password | 비밀 번호 | String |

#### Response

##### Field
```
{
  "success": boolean,
  "accessToken": string
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| success | 인증 성공 여부 | boolean |
| accessToken | access token | String |

### 허용된 CIDR 조회 `인증 필요`
최소 1개, 최대 3개 등록 가능합니다.

#### Request
`[Get] /rest/1/cidrs`

##### Header
```
Authorization: Bearer access-token
```

| 헤더 | 설명 | 타입 |
|----|------------|-----|
| Authorization | 인증 정보 | String |

#### Response

##### Field
```
{
  "cidrs": [...]
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| cidrs | CIDR 리스트 | List\<String\> |

### CIDR 등록 `인증 필요`

#### Request
`[Post] /rest/1/cidr`

##### Header
```
Authorization: Bearer access-token
```

| 헤더 | 설명 | 타입 |
|----|------------|-----|
| Authorization | 인증 정보 | String |

##### Field
```
{
  "cidr": string
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| cidr | CIDR | String |

### CIDR 삭제 `인증 필요`

#### Request
`[Delete] /rest/1/cidr`

##### Header
```
Authorization: Bearer access-token
```

| 헤더 | 설명 | 타입 |
|----|------------|-----|
| Authorization | 인증 정보 | String |

##### Field
```
{
  "cidr": string
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| cidr | CIDR | String |

### 상품 목록 조회 `인증 필요`

#### Request
`[Get] /rest/1/products`

##### Header
```
Authorization: Bearer access-token
```

| 헤더 | 설명 | 타입 |
|----|------------|-----|
| Authorization | 인증 정보 | String |

#### Response
##### Field
```
{
  "products": [
    {
      "code": string,
      "name": string,
      "description": string,
      "price": number,
      "currency": string
    }, ...
   ]
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| products | 상품 목록 | List |

| 필드 | 설명 | 타입 |
|----|------------|-----|
| code | 상품 코드 | String |
| name | 상품 명 | String |
| description | 상품 설명 | String |
| price | 상품 가격 | BigDecimal |
| currency | 통화 | String |

### 상품 주문 `인증 필요`
구매신청을 하면 파일을 제공합니다.

#### Request
`[Post] /rest/1/product`

##### Header
```
Authorization: Bearer access-token
```

| 헤더 | 설명 | 타입 |
|----|------------|-----|
| Authorization | 인증 정보 | String |

##### Field
```
{
    "code": string
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| code | 상품 코드 | String |

#### Response

##### Field
```
{
    "price": number,
    "currency": string,
    "virtualWalletAddress": string
}
```

| 필드 | 설명 | 타입 |
|----|------------|-----|
| price | 상품 가격 | BigDecimal |
| currency | 통화 | String |
| virtualWalletAddress | 입금 가상계좌 주소 | String |

## 7. 상품 종류

- 분기별 리포트 (코로나, 개인건강, 진료 데이터 분석)
- 코로나 백신 접종, 진단 분석 데이터
- 건강 정보 분석 데이터
- 진료 기록 정보 분석 데이터

## 8. 오류

아래 응답 상태 코드들은 오류 표현이 수반될 수 있습니다.

| 상태 코드 | 설명 |
|----| ----------------------------------------------------------------------|
| 400 |    요청 본문의 형식이 잘못되었거나 잘못된 속성 값이 있습니다. |
| 401 |    인증이 필요합니다. |
| 403 |    사용자가 인증되었지만 이 작업에 대한 권한이 없습니다. |
| 404 |    리소스가 존재하지 않거나 이 사용자에게 표시되지 않습니다. |
| 409 | 일부 상태 제약으로 인해 리소스를 생성하거나 수정할 수 없습니다.|
| 500 | DPHR Marketplace에서 예기치 않은 내부 오류가 발생했습니다. |
| 502 | 외부 서비스에 예기치 않은 오류가 발생했습니다.|

## 9. 라이선스

    Copyright 2020 BIONES PTE. LTD.
    BIONES PTE. LTD. licenses this file to you under the Apache License,
    version 2.0 (the "License"); you may not use this file except in compliance
    with the License. You may obtain a copy of the License at:
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations
    under the License.
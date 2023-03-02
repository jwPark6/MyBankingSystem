# 뱅킹 시스템 만들기 - 넘블
![image](https://user-images.githubusercontent.com/67006945/218974487-e5acea99-0d60-4140-85e5-8930dca8b980.png)


## 기간
챌린지 기간: 2023년 2월 10일 - 2022년 3월 10일
구현 기간:

## 규칙
[요구사항 설명 링크](https://www.numble.it/f769d79a-fad2-4314-b41c-e6403961a5d1)

> **하나.** 은행에 필요한 API를 만들어보는 챌린지로 계좌 이체 시스템을 구축합니다.
>
>**둘.** 스프링뿐 아니라 데이터베이스에 대해서도 깊이 있게 학습할 수 있는 챌린지입니다.
>
>**셋.** 동일한 프로젝트를 진행하고 있는 다른 참가자들과 소통과 공유를 통해 더 많은 성장을 이뤄낼 수 있습니다.

## 기술
- Java (language)
- Lombok (Spring boot)
- MySQL (DB)
- Redis (MemoryDB)
- Spring Data Jpa (DB Framework)
- JUnit (Test Framework)
- Redisson (Redis Client)

## API

- Customer
  > **고객 전체 조회** - GET : /customers
  > 
  > **고객 조회** - GET : /customer/{id}
  > 
  > **고객 친구 조회** - GET : /customer/{id}/friends
  > 
  > **고객 등록 (회원가입)** - POST : /customers  -> RequestBody(Customer)

- Friend
  > **친구 목록 조회** - GET : /friends (~~Entity 기준이 Friend 여서 중복되는 값이 조회됨~~)
  > 
  > **친구 추가** - POST : /friend?userId=id&friendUserId=fId
  > 
  > **친구 삭제** - DELETE : /friend?userId=id&friendUserId=fId
  > 
  > **친구 전체 삭제** - DELETE : /friends?userId=id

- Authentication  ~~미구현~~
  > **로그인** - POST : /login
  
- Account
  > **계좌 조회** - GET : /account?userId=id
  >
  > **입금 (Annotation)** - POST : /account/deposit?userId=id&money=m
  >
  > **입금 (단순 메소드)** - POST : /account/lockDeposit?userId=id&money=m
  > 
  > **출금** - POST : /account/withdraw?userId=id&money=m
  > 
  > **계좌 이체** - POST : /account/transfer?userId=id&money=m


## DB 모델링

![image](https://user-images.githubusercontent.com/67006945/222449760-66e6798e-cb6d-40b9-b36e-f05667264065.png)

- CUSTOMER 
    - 간단한 고객정보 테이블

- FRIENDS
    - 고객 친구 목록 테이블

- ACCOUNT
    - 계좌정보 테이블

- TRANSACTION
    - 거래내역 저장 테이블
    - 요구사항이 계좌 이체만 존재하므로 해당 기능만 고려

---

## 계좌 이체 동시성??

@Transaction, MySQL Lock 등 여러가지를 시도했지만 Multi Thread 환경에서 테스팅이 모두 실패했고 최종적으로 Redis, **Redisson** 의 분산락을 활용하여 이를 처리했습니다. 


입금 로직을 Redisson Lock 으로 감싼 이후 이후 내부에서 직접 TransactionManager를 설정하여 로직을 실행하는 방식으로 Unlock, commit 시기를 관리했습니다.

---

## 목표

- [ ]  Method 15 line 이내 작성
- [ ]  3-depth 넘기지 않기!
- [ ]  함수명, 변수명 의미있게 작성하기
- [ ]  단위 테스트 진행하기
- [ ]  예외 처리 최대한 구현하기_

---

## 리팩토링 목표
> 개인 일정 문제와 기본적으로 전체 기능에만 초점을 맞추다 보니 놓친 부분이 너무나도 많습니다. 이 부분들은 결과 제출이후 꾸준하게 수정할 계획이며 대략적으로 생각하는 부분은 다음과 같습니다.
> 
> 1. Package 구조 전체 리팩토링 
>    - 현재 하나의 폴더에 하나의 파일로 구성되어 있어 개선이 필요
>    <br><br>
> 2. DTO -> Request, Response Entity 로 각각 모두 구현하여 데이터를 관리!
>    - 기존 데이터가 크게 유출될 정보가 없다고 판단하여 Entity 그대로 전달하는 경우가 많음
>    - BaseEntity 구현 이후 모든 API 호출에서 DTO 생성하여 전달 예정
>  <br><br>
> 3. Repository 단 DB 데이터 조회 반환 형식 결정
>   - Optional, Null 등등 처리가 필요한 상태, .get() 과 같은 방식으로 데이터를 처리하여 문제가 발생
>  <br><br>
> 4. 로그인 세션
>    - 해당 부분 지식이 부족하여 좀 더 학습 후 확실하게 개선 예정
>  <br><br>
> 5. PK - FK 연관관계
>    - 기존에 DB 모델링할 때 대부분을 Long id 형태로 생성
>    - 그러나 실제로 필요한 Entity 관계에 맞추다 보니 PK 가 아닌 다른 Column에 FK가 설정됨 -Ex) UserId
>    - Entity 모델링 이후 DB 재 모델링 예정
>    - 이와 관련하여 JsonIgnore, Cascade 등 전체 수정 예정
>  <br><br>
> 6. 예외 처리
>    - 전체적인 예외 처리가 모두 필요



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
- Spring Data Jpa (DB Framework)
- JUnit (Test Framework)

## 기능 목록
- 친구 추가 API
- 내 친구 목록 조회 API
- **계좌 이체 API**
- 계좌 조회 API (내 계좌만 조회 가능)
- 회원가입 API
- 송금 및 알람 관련 구조도

## DB 모델링

![image](https://user-images.githubusercontent.com/67006945/219400596-291038eb-b4a1-42fb-9ac2-73209189bc22.png)

- CUSTOMER 
    - 간단한 고객정보 테이블

- FRIENDS
    - 고객 친구 목록 테이블

- ACCOUNT
    - 계좌정보 테이블

- LOG
    - 거래 내역을 저장하기 위한 LOG 테이블
    - 거래 성공/실패 여부를 저장하기 위함

- TRANSACTION
    - 거래내역 저장 테이블
    - 요구사항이 계좌 이체만 존재하므로 해당 기능만 고려

---

## 목표

- [ ]  Method 15 line 이내 작성
- [ ]  3-depth 넘기지 않기!
- [ ]  함수명, 변수명 의미있게 작성하기
- [ ]  단위 테스트 진행하기
- [ ]  예외 처리 최대한 구현하기

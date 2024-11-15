# 편의점

구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템

## 기능 목록

- [x] 입력
    - [x] `products.md` 에서 재고를 읽는다.
    - [x] `promotions.md` 에서 프로모션 할인 정보를 읽는다.
    - [x] 구매할 상품과 수량을 입력받는다. ex) `[콜라-10],[사이다-3]`
    - [x] 프로모션 적용 가능 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
    - [x] 프로모션 재고가 부족하면 정가로 결제할 지 여부를 입력받는다.
    - [x] 멤버십 할인 적용 여부를 입력받는다.
    - [x] 추가 구매 여부를 입력받는다.

- [x] 재고 관리
    - [x] 고객이 상품을 구매하면 재고를 차감한다.
    - [x] 일반 재고와 프로모션 재고가 있다.

- [x] 할인
    - [x] 프로모션 할인
        - [x] N개 구매시 1개를 무료 증정한다.
        - [x] 오늘 날짜가 프로모션 기간 내 포함된 경우에만 적용 가능하다.
        - [x] 하나의 상품에 하나의 프로모션만 적용 가능하다.
        - [x] 프로모션 재고를 우선 차감하며 프로모션 재고 내에서만 적용 가능하다.
        - [x] 프로모션 재고가 부족하면 일반 재고에서 정가로 결제해야 한다.
    - [x] 멤버십 할인
        - [x] 프로모션 미적용 금액의 30%를 할인받는다.
        - [x] 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용한다.
        - [x] 멤버십 할인의 최대 한도는 8,000원이다.

- [x] 구매
    - [x] 재고가 부족하면 구매할 수 없다.
    - [x] 프로모션 및 멤버십 할인 정책에 따라 최종 결제 금액을 계산한다.
    - [x] 프로모션 적용 가능 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
    - [x] 프로모션 재고가 부족하면 정가로 결제할 지 여부에 대해 안내한다.

  - [x] 출력
      - [x] 환영 인사와 함께 상품명, 가격, 프로모션 이름, 재고를 안내한다.
      - [x] 재고가 없으면 `재고 없음`을 출력한다.
      - [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.
      - [x] 멤버십 할인 적용 여부 안내 문구를 출력한다.
      - [x] 영수증 (구매 상품 내역, 증정 상품 내역, 금액 정보)을 출력한다.
      - [x] 추가 구매 여부 안내 문구를 출력한다.
      - [x] 사용자가 잘못된 값을 입력했을 때, "[ERROR]"로 시작하는 오류 메시지와 함께 상황에 맞는 안내를 출력한다.

- [x] 검증 목록
  - [x] 입력
    - [x] 구매할 상품과 수량 형식을 검증한다.
    - [x] Y/N 입력에는 Y, N 외에는 올 수 없다.
    
  - [x] 재고
    - [x] 상품 수량은 음수일 수 없다.
    - [x] 상품 이름은 2자 이상 10자 이하이다.
    - [x] 상품 가격은 100원 이상 10만원 이하이다.

  - [x] 구매
    - [x] 구매 수량은 0이하일 수 없다. 
    - [x] 하나의 상품은 한 번에걸쳐 구매 가능하다.
    
  - [x] 프로모션
    - [x] 프로모션 이름은 4자 이상 12자 이하이다.
    - [x] 프로모션의 Buy는 1부터 최대 4까지 가능하다.
    - [x] 프로모션의 Get은 반드시 1이다.